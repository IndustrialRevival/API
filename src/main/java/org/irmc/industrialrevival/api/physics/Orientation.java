package org.irmc.industrialrevival.api.physics;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface Orientation {
    Orientation UPRIGHT = new UprightOrientation();
    Orientation INVERTED = new InvertedOrientation();
    Orientation SIDEWAYS = new SidewaysOrientation();
    class CustomAngle implements CustomAngleOrientation, Cloneable {
        private double angle;

        @Override
        public double angle() {
            return angle;
        }

        @Override
        public void angle(double angle) {
            this.angle = angle;
        }

        @Override
        public CustomAngle clone() {
            try {
                return (CustomAngle) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    int value();

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class UprightOrientation implements Orientation {
        @Override
        public int value() {
            return 0;
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class InvertedOrientation implements Orientation {
        @Override
        public int value() {
            return 1;
        }
    }

    @ApiStatus.Internal
    @ApiStatus.NonExtendable
    class SidewaysOrientation implements Orientation {
        @Override
        public int value() {
            return 2;
        }
    }

    interface CustomAngleOrientation extends Orientation {
        @Override
        default int value() {
            return 3;
        }

        double angle();
        void angle(double angle);
    }
}