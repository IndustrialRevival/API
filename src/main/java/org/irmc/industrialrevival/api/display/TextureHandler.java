package org.irmc.industrialrevival.api.display;

import org.irmc.industrialrevival.api.display.builder.TextModelBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

/**
 * Handler for managing textures in displays.
 * Processes corners and text model builders to create appropriate textures.
 *
 * @author balugaq
 */
public interface TextureHandler extends BiConsumer<Corners, TextModelBuilder> {
    /**
     * Performs the operation represented by this handler.
     *
     * @param corners      the corners defining the display area
     * @param extraHandler the text model builder to use
     */
    @Override
    void accept(@Nonnull Corners corners, @Nullable TextModelBuilder extraHandler);
}