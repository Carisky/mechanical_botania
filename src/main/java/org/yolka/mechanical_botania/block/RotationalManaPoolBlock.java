package org.yolka.mechanical_botania.block;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.yolka.mechanical_botania.blockentity.RotationalManaPoolBlockEntity;

import javax.annotation.Nullable;

public class RotationalManaPoolBlock extends RotatedPillarKineticBlock implements EntityBlock {
    public RotationalManaPoolBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RotationalManaPoolBlockEntity(pos, state);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }
}
