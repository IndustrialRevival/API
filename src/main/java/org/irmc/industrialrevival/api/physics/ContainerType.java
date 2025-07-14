package org.irmc.industrialrevival.api.physics;

import net.kyori.adventure.text.Component;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface ContainerType extends Keyed {
    ContainerType BOTTLE = new BottleContainerType();
    ContainerType BUCKET = new BucketContainerType();
    ContainerType SMALL_TANK = new SmallTankContainerType();
    ContainerType MEDIUM_TANK = new MediumTankContainerType();
    ContainerType LARGE_TANK = new LargeTankContainerType();
    ContainerType SMALL_MACHINE = new SmallMachineContainerType();
    ContainerType MEDIUM_MACHINE = new MediumMachineContainerType();
    ContainerType LARGE_MACHINE = new LargeMachineContainerType();

    long getCapacity();
    @NotNull Component getName();

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class BottleContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.Bottle;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Bottle");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("bottle");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class BucketContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.Bucket;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Bucket");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("bucket");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class SmallTankContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.SmallTank;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Small Tank");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("small_tank");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class MediumTankContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.MediumTank;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Medium Tank");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("medium_tank");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class LargeTankContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.LargeTank;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Large Tank");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("large_tank");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class SmallMachineContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.SmallMachine;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Small Machine");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("small_machine");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class MediumMachineContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.MediumMachine;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Medium Machine");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("medium_machine");
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class LargeMachineContainerType implements ContainerType {
        @Override
        public long getCapacity() {
            return Constants.Values.ContainerCapacity.LargeMachine;
        }

        @Override
        public @NotNull Component getName() {
            return Component.text("Large Machine");
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return KeyUtil.customKey("large_machine");
        }
    }
}
