package org.irmc.industrialrevival.api.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * A Valence is a set of valences.
 * @param valences the valences
 * @author balugaq
 */
public record Valence(@Range(from = -8, to = 8) int @NotNull ... valences) {
    public static @NotNull Valence of(@Range(from = -8, to = 8) int @NotNull ... valences) {
        return new Valence(valences);
    }
}
