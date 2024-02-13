package net.dazeful.vanilljabiomes.block;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.Thickness;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CrystalSpireBlock
        extends Block
        implements LandingBlock,
        Waterloggable {
    public static final MapCodec<CrystalSpireBlock> CODEC = createCodec(CrystalSpireBlock::new);
    public static final DirectionProperty VERTICAL_DIRECTION = Properties.VERTICAL_DIRECTION;
    public static final EnumProperty<Thickness> THICKNESS = Properties.THICKNESS;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final int field_31205 = 11;
    private static final int field_31207 = 2;
    private static final float field_31208 = 0.02f;
    private static final float field_31209 = 0.12f;
    private static final int field_31210 = 11;
    private static final float WATER_DRIP_CHANCE = 0.17578125f;
    private static final float LAVA_DRIP_CHANCE = 0.05859375f;
    private static final double field_31213 = 0.6;
    private static final float field_31214 = 1.0f;
    private static final int field_31215 = 40;
    private static final int field_31200 = 6;
    private static final float field_31201 = 2.0f;
    private static final int field_31202 = 2;
    private static final float field_33566 = 5.0f;
    private static final float field_33567 = 0.011377778f;
    private static final int MAX_STALACTITE_GROWTH = 7;
    private static final int STALACTITE_FLOOR_SEARCH_RANGE = 10;
    private static final float field_31203 = 0.6875f;
    private static final VoxelShape TIP_MERGE_SHAPE = createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape UP_TIP_SHAPE = createCuboidShape(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
    private static final VoxelShape DOWN_TIP_SHAPE = createCuboidShape(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape BASE_SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    private static final VoxelShape FRUSTUM_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    private static final VoxelShape MIDDLE_SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    private static final float MAX_HORIZONTAL_MODEL_OFFSET = 0.125f;
    private static final VoxelShape DRIP_COLLISION_SHAPE = createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    public MapCodec<CrystalSpireBlock> getCodec() {
        return CODEC;
    }

    public CrystalSpireBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(VERTICAL_DIRECTION, Direction.UP).with(THICKNESS, Thickness.TIP).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VERTICAL_DIRECTION, THICKNESS, WATERLOGGED);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return this.canPlaceAtWithDirection(world, pos, state.get(VERTICAL_DIRECTION));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction neighborDirection, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (neighborDirection != Direction.UP && neighborDirection != Direction.DOWN) {
            return state;
        }
        Direction direction = state.get(VERTICAL_DIRECTION);
        if (direction == Direction.DOWN && world.getBlockTickScheduler().isQueued(pos, this)) {
            return state;
        }
        if (neighborDirection == direction.getOpposite() && !this.canPlaceAt(state, world, pos)) {
            if (direction == Direction.DOWN) {
                world.scheduleBlockTick(pos, this, 2);
            } else {
                world.scheduleBlockTick(pos, this, 1);
            }
            return state;
        }
        boolean thicknessIsTipMerge = state.get(THICKNESS) == Thickness.TIP_MERGE;
        Thickness thickness = this.getThickness(world, pos, direction, thicknessIsTipMerge);
        return state.with(THICKNESS, thickness);
    }

    @Override
    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (world.isClient) {
            return;
        }
        BlockPos blockPos = hit.getBlockPos();
        if (projectile.canModifyAt(world, blockPos) && projectile.canBreakBlocks(world) && projectile instanceof TridentEntity && projectile.getVelocity().length() > 0.6) {
            world.breakBlock(blockPos, true);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (state.get(VERTICAL_DIRECTION) == Direction.UP && state.get(THICKNESS) == Thickness.TIP) {
            entity.handleFallDamage(fallDistance + 2.0f, 2.0f, world.getDamageSources().stalagmite());
        } else {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!this.canDrip(state)) {
            return;
        }
        float f = random.nextFloat();
        if (f > 0.12f) {
            return;
        }
        this.getFluid(world, pos, state).filter(fluid -> f < 0.02f || isFluidStill(fluid.fluid)).ifPresent(fluid -> createParticle(world, pos, state, fluid.fluid));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.isPointingUp(state) && !this.canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
        } else {
            this.spawnFallingBlock(state, world, pos);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.dripTick(state, world, pos, random.nextFloat());
        if (random.nextFloat() < field_33567 && this.isHeldByPointedDripstone(state, world, pos)) {
            this.tryGrow(state, world, pos, random);
        }
    }

    @VisibleForTesting
    public void dripTick(BlockState state, ServerWorld world, BlockPos pos, float dripChance) {
        float f;
        if (dripChance > 0.17578125f) {
            return;
        }
        if (!this.isHeldByPointedDripstone(state, world, pos)) {
            return;
        }
        Optional<DrippingFluid> optional = this.getFluid(world, pos, state);
        if (optional.isEmpty()) {
            return;
        }
        Fluid fluid = optional.get().fluid;
        if (fluid == Fluids.WATER) {
            f = WATER_DRIP_CHANCE;
        } else if (fluid == Fluids.LAVA) {
            f = LAVA_DRIP_CHANCE;
        } else {
            return;
        }
        if (dripChance >= f) {
            return;
        }
        BlockPos blockPos = this.getTipPos(state, world, pos, 11, false);
        if (blockPos == null) {
            return;
        }
        if (optional.get().sourceState.isOf(Blocks.MUD) && fluid == Fluids.WATER) {
            BlockState blockState = Blocks.CLAY.getDefaultState();
            world.setBlockState(optional.get().pos, blockState);
            Block.pushEntitiesUpBeforeBlockChange(optional.get().sourceState, blockState, world, optional.get().pos);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS, blockPos, 0);
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos;
        World worldAccess = ctx.getWorld();
        Direction directionToPlaceAt = this.getDirectionToPlaceAt(worldAccess, blockPos = ctx.getBlockPos(), ctx.getVerticalPlayerLookDirection().getOpposite());
        if (directionToPlaceAt == null) {
            return null;
        }
        boolean shouldNotCancelInteraction = !ctx.shouldCancelInteraction();
        Thickness thickness = this.getThickness(worldAccess, blockPos, directionToPlaceAt, shouldNotCancelInteraction);
        if (thickness == null) {
            return null;
        }
        return this.getDefaultState()
                .with(VERTICAL_DIRECTION, directionToPlaceAt)
                .with(THICKNESS, thickness)
                .with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Thickness thickness = state.get(THICKNESS);
        VoxelShape voxelShape = switch (thickness) {
            case TIP_MERGE -> TIP_MERGE_SHAPE;
            case TIP -> this.isPointingDown(state) ? DOWN_TIP_SHAPE : UP_TIP_SHAPE;
            case FRUSTUM -> BASE_SHAPE;
            case MIDDLE -> FRUSTUM_SHAPE;
            case BASE -> MIDDLE_SHAPE;
        };
        Vec3d modelOffset = state.getModelOffset(world, pos);
        return voxelShape.offset(modelOffset.x, 0.0, modelOffset.z);
    }

    @Override
    protected boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    protected float getMaxHorizontalModelOffset() {
        return MAX_HORIZONTAL_MODEL_OFFSET;
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_LANDS, pos, 0);
        }
    }

    @Override
    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingStalactite(attacker);
    }

    private void spawnFallingBlock(BlockState state, ServerWorld world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        BlockState blockState = state;
        while (this.isPointingDown(blockState)) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, mutable, blockState);
            if (this.isTip(blockState, true)) {
                int fallHurtAmount = Math.max(1 + pos.getY() - mutable.getY(), 6);
                fallingBlockEntity.setHurtEntities(fallHurtAmount, 40);
                break;
            }
            mutable.move(Direction.DOWN);
            blockState = world.getBlockState(mutable);
        }
    }

    @VisibleForTesting
    public void tryGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState blockStateAbove = world.getBlockState(pos.up(1));
        if (!this.canGrow(blockStateAbove, world.getBlockState(pos.up(2)))) {
            return;
        }
        BlockPos tipPos = this.getTipPos(state, world, pos, MAX_STALACTITE_GROWTH, false);
        if (tipPos == null) {
            return;
        }
        BlockState tipBlockState = world.getBlockState(tipPos);
        if (!this.canDrip(tipBlockState) || !this.canGrow(tipBlockState, world, tipPos)) {
            return;
        }
        if (random.nextBoolean()) {
            this.tryGrow(world, tipPos, Direction.DOWN);
        } else {
            this.tryGrowStalagmite(world, tipPos);
        }
    }

    private void tryGrowStalagmite(ServerWorld world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (int i = 0; i < STALACTITE_FLOOR_SEARCH_RANGE; ++i) {
            mutable.move(Direction.DOWN);
            BlockState blockState = world.getBlockState(mutable);
            if (!blockState.getFluidState().isEmpty()) {
                return;
            }
            if (this.isTip(blockState, Direction.UP) && this.canGrow(blockState, world, mutable)) {
                this.tryGrow(world, mutable, Direction.UP);
                return;
            }
            if (this.canPlaceAtWithDirection(world, mutable, Direction.UP) && !world.isWater(mutable.down())) {
                this.tryGrow(world, mutable.down(), Direction.UP);
                return;
            }
            if (canDripThrough(world, mutable, blockState)) continue;
            return;
        }
    }

    private void tryGrow(ServerWorld world, BlockPos pos, Direction direction) {
        BlockPos posToGrowTo = pos.offset(direction);
        BlockState stateInPosToGrowTo = world.getBlockState(posToGrowTo);
        if (this.isTip(stateInPosToGrowTo, direction.getOpposite())) {
            this.growMerged(stateInPosToGrowTo, world, posToGrowTo);
        } else if (stateInPosToGrowTo.isAir() || stateInPosToGrowTo.isOf(Blocks.WATER)) {
            this.place(world, posToGrowTo, direction, Thickness.TIP);
        }
    }

    private void place(WorldAccess world, BlockPos pos, Direction direction, Thickness thickness) {
        BlockState blockState = this.getDefaultState()
                .with(VERTICAL_DIRECTION, direction)
                .with(THICKNESS, thickness)
                .with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
        world.setBlockState(pos, blockState, 3);
    }

    private void growMerged(BlockState state, WorldAccess world, BlockPos pos) {
        BlockPos blockPos2;
        BlockPos blockPos;
        if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
            blockPos = pos;
            blockPos2 = pos.up();
        } else {
            blockPos2 = pos;
            blockPos = pos.down();
        }
        this.place(world, blockPos2, Direction.DOWN, Thickness.TIP_MERGE);
        this.place(world, blockPos, Direction.UP, Thickness.TIP_MERGE);
    }

    private static void createParticle(World world, BlockPos pos, BlockState state, Fluid fluid) {
        Vec3d modelOffset = state.getModelOffset(world, pos);
        double d = 0.0625;
        double particleX = pos.getX() + 0.5 + modelOffset.x;
        double particleY = pos.getY() + 1 - field_31203 - d;
        double particleZ = pos.getZ() + 0.5 + modelOffset.z;
        Fluid dripFluid = getDripFluid(world, fluid);
        DefaultParticleType particleEffect = dripFluid.isIn(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        world.addParticle(particleEffect, particleX, particleY, particleZ, 0.0, 0.0, 0.0);
    }

    @Nullable
    private BlockPos getTipPos(BlockState state, WorldAccess world, BlockPos pos, int range, boolean allowMerged) {
        if (this.isTip(state, allowMerged)) {
            return pos;
        }
        Direction direction = state.get(VERTICAL_DIRECTION);
        BiPredicate<BlockPos, BlockState> continuePredicate = (posx, statex) -> statex.isOf(this) && statex.get(VERTICAL_DIRECTION) == direction;
        return searchInDirection(world, pos, direction.getDirection(), continuePredicate, statex -> this.isTip(statex, allowMerged), range).orElse(null);
    }

    @Nullable
    private Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        Direction directionToPlaceAt;
        if (this.canPlaceAtWithDirection(world, pos, direction)) {
            directionToPlaceAt = direction;
        } else if (this.canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
            directionToPlaceAt = direction.getOpposite();
        } else {
            return null;
        }
        return directionToPlaceAt;
    }

    private Thickness getThickness(WorldView world, BlockPos pos, Direction direction, boolean tryMerge) {
        Direction direction2 = direction.getOpposite();
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (this.isCustomPointedDripstoneFacingDirection(blockState, direction2)) {
            if (tryMerge || blockState.get(THICKNESS) == Thickness.TIP_MERGE) {
                return Thickness.TIP_MERGE;
            }
            return Thickness.TIP;
        }
        if (!this.isCustomPointedDripstoneFacingDirection(blockState, direction)) {
            return Thickness.TIP;
        }
        Thickness thickness = blockState.get(THICKNESS);
        if (thickness == Thickness.TIP || thickness == Thickness.TIP_MERGE) {
            return Thickness.FRUSTUM;
        }
        BlockState blockState2 = world.getBlockState(pos.offset(direction2));
        if (!this.isCustomPointedDripstoneFacingDirection(blockState2, direction)) {
            return Thickness.BASE;
        }
        return Thickness.MIDDLE;
    }

    public boolean canDrip(BlockState state) {
        return this.isPointingDown(state) && state.get(THICKNESS) == Thickness.TIP && !state.get(WATERLOGGED);
    }

    private boolean canGrow(BlockState state, ServerWorld world, BlockPos pos) {
        Direction direction = state.get(VERTICAL_DIRECTION);
        BlockPos posToGrowTo = pos.offset(direction);
        BlockState stateInPosToGrowTo = world.getBlockState(posToGrowTo);
        if (!stateInPosToGrowTo.getFluidState().isEmpty()) {
            return false;
        }
        if (stateInPosToGrowTo.isAir()) {
            return true;
        }
        return this.isTip(stateInPosToGrowTo, direction.getOpposite());
    }

    private Optional<BlockPos> getSupportingPos(World world, BlockPos pos, BlockState state, int range) {
        Direction direction = state.get(VERTICAL_DIRECTION);
        BiPredicate<BlockPos, BlockState> continuePredicate = (posx, statex) -> statex.isOf(this) && statex.get(VERTICAL_DIRECTION) == direction;
        return searchInDirection(world, pos, direction.getOpposite().getDirection(), continuePredicate, statex -> !statex.isOf(this), range);
    }

    private boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction) || this.isCustomPointedDripstoneFacingDirection(blockState, direction);
    }

    private boolean isTip(BlockState state, boolean allowMerged) {
        if (!state.isOf(this)) {
            return false;
        }
        Thickness thickness = state.get(THICKNESS);
        return thickness == Thickness.TIP || allowMerged && thickness == Thickness.TIP_MERGE;
    }

    private boolean isTip(BlockState state, Direction direction) {
        return this.isTip(state, false) && state.get(VERTICAL_DIRECTION) == direction;
    }

    private boolean isPointingDown(BlockState state) {
        return this.isCustomPointedDripstoneFacingDirection(state, Direction.DOWN);
    }

    private boolean isPointingUp(BlockState state) {
        return this.isCustomPointedDripstoneFacingDirection(state, Direction.UP);
    }

    private boolean isHeldByPointedDripstone(BlockState state, WorldView world, BlockPos pos) {
        return this.isPointingDown(state) && !world.getBlockState(pos.up()).isOf(this);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private boolean isCustomPointedDripstoneFacingDirection(BlockState state, Direction direction) {
        return state.isOf(this) && state.get(VERTICAL_DIRECTION) == direction;
    }

    private Optional<DrippingFluid> getFluid(World world, BlockPos pos, BlockState state) {
        if (!this.isPointingDown(state)) {
            return Optional.empty();
        }
        return this.getSupportingPos(world, pos, state, 11).map(posx -> {
            BlockPos blockPos = posx.up();
            BlockState blockState = world.getBlockState(blockPos);
            Fluid fluid = blockState.isOf(Blocks.MUD) && !world.getDimension().ultrawarm() ? Fluids.WATER : world.getFluidState(blockPos).getFluid();
            return new DrippingFluid(blockPos, fluid, blockState);
        });
    }

    private static boolean isFluidStill(Fluid fluid) {
        return fluid == Fluids.LAVA || fluid == Fluids.WATER;
    }

    private boolean canGrow(BlockState dripstoneBlockState, BlockState waterState) {
        return dripstoneBlockState.isOf(Blocks.DRIPSTONE_BLOCK) && waterState.isOf(Blocks.WATER) && waterState.getFluidState().isStill();
    }

    private static Fluid getDripFluid(World world, Fluid fluid) {
        if (fluid.matchesType(Fluids.EMPTY)) {
            return world.getDimension().ultrawarm() ? Fluids.LAVA : Fluids.WATER;
        }
        return fluid;
    }

    private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction,
                                                        BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
        Direction direction2 = Direction.get(direction, Direction.Axis.Y);
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (int i = 1; i < range; ++i) {
            mutable.move(direction2);
            BlockState blockState = world.getBlockState(mutable);
            if (stopPredicate.test(blockState)) {
                return Optional.of(mutable.toImmutable());
            }
            if (!world.isOutOfHeightLimit(mutable.getY()) && continuePredicate.test(mutable, blockState)) continue;
            return Optional.empty();
        }
        return Optional.empty();
    }

    private static boolean canDripThrough(BlockView world, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        }
        if (state.isOpaqueFullCube(world, pos)) {
            return false;
        }
        if (!state.getFluidState().isEmpty()) {
            return false;
        }
        VoxelShape collisionShape = state.getCollisionShape(world, pos);
        return !VoxelShapes.matchesAnywhere(DRIP_COLLISION_SHAPE, collisionShape, BooleanBiFunction.AND);
    }

    record DrippingFluid(BlockPos pos, Fluid fluid, BlockState sourceState) {
    }
}