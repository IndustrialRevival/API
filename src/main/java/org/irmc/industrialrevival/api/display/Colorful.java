package org.irmc.industrialrevival.api.display;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.TextDisplay;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Colorful interface provides functionality for creating colored displays in the game world.
 * It allows creation of color-based visual elements using text displays.
 *
 * @author balugaq
 */
public interface Colorful {
    /**
     * Creates a new Builder instance for constructing displays.
     *
     * @return a new Builder instance
     */
    default public Builder brush() {
        return new Builder().modelHandler(getModelHandler());
    }

    /**
     * Gets the model handler associated with this colorful display.
     *
     * @return the model handler
     */
    ModelHandler getModelHandler();

    /**
     * Renders the display at the specified location.
     *
     * @param center the location to render the display at
     * @return the display group created
     */
    DisplayGroup renderAt(Location center);

    /**
     * Builder class for constructing colorful displays.
     * Provides methods to configure and build display objects.
     *
     * @author balugaq
     */
    @Getter
    @Setter
    public static class Builder {
        @Getter
        private IndustrialRevivalAddon plugin = null;
        @Getter
        private Color color = null;
        @Getter
        private Location center = null;
        @Getter
        private double offsetPX = 0D;
        @Getter
        private double offsetPY = 0D;
        @Getter
        private double offsetPZ = 0D;
        @Getter
        private double offsetNX = 0D;
        @Getter
        private double offsetNY = 0D;
        @Getter
        private double offsetNZ = 0D;
        @Getter
        private List<ColorBlock> render = new ArrayList<>(List.of(ColorBlock.DEFAULT_SURFACE));
        @Getter
        private TextureHandler textureHandler = null;
        @Getter
        private ModelHandler modelHandler = null;

        /**
         * Sets the plugin responsible for this builder.
         *
         * @param plugin the plugin to set
         * @return this builder instance
         */
        public Builder plugin(IndustrialRevivalAddon plugin) {
            this.plugin = plugin;
            return this;
        }

        /**
         * Sets the color for the display.
         *
         * @param color the color to set
         * @return this builder instance
         */
        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        /**
         * Sets the color for the display using a TextColor.
         *
         * @param color the text color to convert and set
         * @return this builder instance
         */
        public Builder color(TextColor color) {
            this.color = Color.fromRGB(color.red(), color.blue(), color.green());
            return this;
        }

        /**
         * Sets the center location based on a block.
         *
         * @param block the block whose location to use as center
         * @return this builder instance
         */
        public Builder center(Block block) {
            return center(block.getLocation());
        }

        /**
         * Sets the center location for the display.
         *
         * @param location the location to set as center
         * @return this builder instance
         */
        public Builder center(Location location) {
            this.center = location;
            return this;
        }

        /**
         * Sets the center location using world coordinates.
         *
         * @param world the world to use
         * @param x     the x-coordinate
         * @param y     the y-coordinate
         * @param z     the z-coordinate
         * @return this builder instance
         */
        public Builder center(World world, double x, double y, double z) {
            this.center = new Location(world, x, y, z);
            return this;
        }

        /**
         * Extends the display by the specified amounts.
         *
         * @param x the amount to extend in x direction
         * @param y the amount to extend in y direction
         * @param z the amount to extend in z direction
         * @return this builder instance
         */
        public Builder extend(double x, double y, double z) {
            if (x > 0) offsetPX += x;
            else offsetNX += x;
            if (y > 0) offsetPY += y;
            else offsetNY += y;
            if (z > 0) offsetPZ += z;
            else offsetNZ += z;
            return this;
        }

        /**
         * Extends the display in x direction.
         *
         * @param x the amount to extend
         * @return this builder instance
         */
        public Builder extendX(double x) {
            return extend(x, 0, 0);
        }

        /**
         * Extends the display in y direction.
         *
         * @param y the amount to extend
         * @return this builder instance
         */
        public Builder extendY(double y) {
            return extend(0, y, 0);
        }

        /**
         * Extends the display in z direction.
         *
         * @param z the amount to extend
         * @return this builder instance
         */
        public Builder extendZ(double z) {
            return extend(0, 0, z);
        }

        /**
         * Sets the extension in x direction.
         *
         * @param x the value to set
         * @return this builder instance
         */
        public Builder x(double x) {
            return extendX(x);
        }

        /**
         * Sets the extension in y direction.
         *
         * @param y the value to set
         * @return this builder instance
         */
        public Builder y(double y) {
            return extendY(y);
        }

        /**
         * Sets the extension in z direction.
         *
         * @param z the value to set
         * @return this builder instance
         */
        public Builder z(double z) {
            return extendZ(z);
        }

        /**
         * Sets the exact extension limits for the display.
         *
         * @param x the extension limit in x direction
         * @param y the extension limit in y direction
         * @param z the extension limit in z direction
         * @return this builder instance
         */
        public Builder extendTo(double x, double y, double z) {
            if (x > 0) offsetPX = x;
            else offsetNX = x;
            if (y > 0) offsetPY = y;
            else offsetNY = y;
            if (z > 0) offsetPZ = z;
            else offsetNZ = z;
            return this;
        }

        /**
         * Sets the exact extension limit in x direction.
         *
         * @param x the extension limit to set
         * @return this builder instance
         */
        public Builder extendXTo(double x) {
            if (x > 0) offsetPX = x;
            else offsetNX = x;
            return this;
        }

        /**
         * Sets the exact extension limit in y direction.
         *
         * @param y the extension limit to set
         * @return this builder instance
         */
        public Builder extendYTo(double y) {
            if (y > 0) offsetPY = y;
            else offsetNY = y;
            return this;
        }

        /**
         * Sets the exact extension limit in z direction.
         *
         * @param z the extension limit to set
         * @return this builder instance
         */
        public Builder extendZTo(double z) {
            if (z > 0) offsetPZ = z;
            else offsetNZ = z;
            return this;
        }

        /**
         * Sets the color blocks to render.
         *
         * @param colorBlocks the color blocks to set
         * @return this builder instance
         */
        public Builder render(ColorBlock... colorBlocks) {
            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                render = new ArrayList<>(List.of(colorBlocks));
            } else {
                render.addAll(List.of(colorBlocks));
            }
            return this;
        }

        /**
         * Sets the texture handler for the display.
         *
         * @param textureHandler the texture handler to set
         * @return this builder instance
         */
        public Builder textureHandler(TextureHandler textureHandler) {
            this.textureHandler = textureHandler;
            return this;
        }

        /**
         * Sets the model handler for the display.
         *
         * @param modelHandler the model handler to set
         * @return this builder instance
         */
        public Builder modelHandler(ModelHandler modelHandler) {
            this.modelHandler = modelHandler;
            return this;
        }

        /**
         * Builds and returns the configured display group.
         *
         * @return the created display group
         */
        public DisplayGroup build() {
            Preconditions.checkNotNull(plugin, "Plugin is not set");
            Preconditions.checkNotNull(color, "Color is not set");
            Preconditions.checkNotNull(center, "Center is not set");
            Preconditions.checkArgument(render.size() != 0, "Render is not set");

            Corners corners = Corners.of(center);
            corners
                    .merge(Corners.of(center.clone().add(offsetPX, offsetPY, offsetPZ)))
                    .merge(Corners.of(center.clone().add(offsetNX, offsetNY, offsetNZ)));

            List<TextDisplay> displays = new ArrayList<>();

            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                displays = ColorBlock.makeSurface(corners, color, textureHandler);
            } else {
                for (ColorBlock colorBlock : render) {
                    displays.add(colorBlock.make(corners, color, textureHandler));
                }
            }

            var group = new DisplayGroup(plugin);
            for (var display : displays) {
                group.addDirectly(display);
            }

            if (modelHandler != null) {
                modelHandler.addDisplayGroup(center, group);
            }

            return group;
        }

        /**
         * Builds and returns the list of text displays.
         *
         * @return the list of created text displays
         */
        public List<TextDisplay> build0() {
            Preconditions.checkNotNull(plugin, "Plugin is not set");
            Preconditions.checkNotNull(color, "Color is not set");
            Preconditions.checkNotNull(center, "Center is not set");
            Preconditions.checkArgument(render.size() != 0, "Render is not set");

            Corners corners = Corners.of(center);
            corners
                    .merge(Corners.of(center.clone().add(offsetPX, offsetPY, offsetPZ)))
                    .merge(Corners.of(center.clone().add(offsetNX, offsetNY, offsetNZ)));

            List<TextDisplay> displays = new ArrayList<>();

            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                displays = ColorBlock.makeSurface(corners, color, textureHandler);
            } else {
                for (ColorBlock colorBlock : render) {
                    displays.add(colorBlock.make(corners, color, textureHandler));
                }
            }

            return displays;
        }
    }
}