package org.yolka.mechanical_botania.blockentity;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.yolka.mechanical_botania.registry.ModBlockEntities;
import vazkii.botania.api.mana.ManaPool;

import java.util.Optional;

public class RotationalManaPoolBlockEntity extends SimpleKineticBlockEntity implements ManaPool {
    private static final int MAX_MANA = 100000;
    private int mana = 0;
    private Optional<DyeColor> color = Optional.empty();

    public RotationalManaPoolBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROTATIONAL_MANA_POOL.get(), pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (level != null && !level.isClientSide) {
            float speed = getSpeed();
            if (speed != 0 && mana < MAX_MANA) {
                mana = Math.min(MAX_MANA, mana + (int) Math.abs(speed));
                setChanged();
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Mana", mana);
        color.ifPresent(c -> tag.putInt("Color", c.getId()));
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        mana = tag.getInt("Mana");
        if (tag.contains("Color")) {
            color = Optional.of(DyeColor.byId(tag.getInt("Color")));
        } else {
            color = Optional.empty();
        }
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putInt("Mana", mana);
        color.ifPresent(c -> tag.putInt("Color", c.getId()));
    }

    // ManaPool implementation
    @Override
    public Level getManaReceiverLevel() {
        return level;
    }

    @Override
    public BlockPos getManaReceiverPos() {
        return worldPosition;
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    @Override
    public boolean isFull() {
        return mana >= MAX_MANA;
    }

    @Override
    public void receiveMana(int mana) {
        this.mana = Math.min(MAX_MANA, this.mana + mana);
    }

    @Override
    public boolean canReceiveManaFromBursts() {
        return true;
    }

    @Override
    public boolean isOutputtingPower() {
        return true;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public Optional<DyeColor> getColor() {
        return color;
    }

    @Override
    public void setColor(Optional<DyeColor> color) {
        this.color = color;
    }
}
