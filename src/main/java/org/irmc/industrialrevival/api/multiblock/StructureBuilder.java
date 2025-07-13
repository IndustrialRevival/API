package org.irmc.industrialrevival.api.multiblock;

import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;
import org.jetbrains.annotations.NotNull;

/**
 * Builder class for constructing multi-block structures in a flexible and incremental way.
 * <p>
 * This class provides a fluent API for assembling 3D structures layer by layer, row by row, or column by column.
 * It allows for setting the center, replacing pieces, and finally building a {@link Structure} instance.
 * </p>
 *
 * @author balugaq
 * @see Structure
 */
@SuppressWarnings("unused")
public class StructureBuilder {
    public int[] center;
    public StructurePiece[][][] pieces;

    /**
     * Constructs a StructureBuilder with the default center at (0, 0, 0).
     */
    public StructureBuilder() {
        center = new int[]{0, 0, 0};
    }

    /**
     * Constructs a StructureBuilder with the specified center coordinates.
     *
     * @param x the x-coordinate of the center
     * @param y the y-coordinate of the center
     * @param z the z-coordinate of the center
     */
    public StructureBuilder(int x, int y, int z) {
        this();
        this.center = new int[]{x, y, z};
    }

    /**
     * Sets a single column in the structure.
     *
     * @param layerIndex the layer index
     * @param rowIndex the row index
     * @param colIndex the column index
     * @param piece the structure piece to set
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setColumn(int layerIndex, int rowIndex, int colIndex, @NotNull StructurePiece piece) {
        pieces[layerIndex][rowIndex][colIndex] = piece;
        return this;
    }

    /**
     * Sets an entire row in the structure.
     *
     * @param layerIndex the layer index
     * @param rowIndex the row index
     * @param pieces the array of structure pieces for the row
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setRow(int layerIndex, int rowIndex, @NotNull StructurePiece[] pieces) {
        System.arraycopy(pieces, 0, this.pieces[layerIndex][rowIndex], 0, pieces.length);
        return this;
    }

    /**
     * Sets an entire layer in the structure.
     *
     * @param layerIndex the layer index
     * @param pieces the 2D array of structure pieces for the layer
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setLayer(int layerIndex, @NotNull StructurePiece[][] pieces) {
        System.arraycopy(pieces, 0, this.pieces[layerIndex], 0, pieces.length);
        return this;
    }

    /**
     * Sets the entire 3D array of structure pieces.
     *
     * @param pieces the 3D array of structure pieces
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setPieces(@NotNull StructurePiece[][][] pieces) {
        this.pieces = pieces;
        return this;
    }

    /**
     * Replaces all occurrences of a specific piece with another piece in the structure.
     *
     * @param piece the piece to replace
     * @param replacement the replacement piece
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder replaceAll(StructurePiece piece, StructurePiece replacement) {
        for (int layerIndex = 0; layerIndex < pieces.length; layerIndex++) {
            for (int rowIndex = 0; rowIndex < pieces[layerIndex].length; rowIndex++) {
                for (int colIndex = 0; colIndex < pieces[layerIndex][rowIndex].length; colIndex++) {
                    if (pieces[layerIndex][rowIndex][colIndex] == piece) {
                        pieces[layerIndex][rowIndex][colIndex] = replacement;
                    }
                }
            }
        }
        return this;
    }

    /**
     * Sets the center of the structure.
     *
     * @param center the center coordinates as an array
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setCenter(int[] center) {
        this.center = center;
        return this;
    }

    /**
     * Sets the center of the structure using x, y, z coordinates.
     *
     * @param x the x-coordinate of the center
     * @param y the y-coordinate of the center
     * @param z the z-coordinate of the center
     * @return this StructureBuilder instance for method chaining
     */
    public StructureBuilder setCenter(int x, int y, int z) {
        return this.setCenter(new int[]{x, y, z});
    }

    /**
     * Builds and returns the Structure instance based on the current configuration.
     *
     * @return the constructed Structure
     */
    public Structure build() {
        return new Structure(this.pieces, this.center);
    }
}
