package org.irmc.industrialrevival.api.multiblock;

import org.bukkit.Material;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.multiblock.piece.AnyStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.IRBlockStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.MaterialStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.SectionStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;

/**
 * Utility class for creating and manipulating multi-block structure pieces and templates.
 * <p>
 * Provides a variety of static helper methods for generating layers, rows, cubes, outlines,
 * and other common 2D/3D arrangements of structure pieces, using either Bukkit Materials or
 * IndustrialRevivalItem objects. Also provides factory methods for special structure pieces.
 * </p>
 *
 * @author balugaq
 * @see StructurePiece
 * @see Structure
 */
public class StructureUtil {
    /**
     * Creates a square 2D layer of the specified material.
     *
     * @param material the material to fill the layer
     * @param size the size of the layer (size x size)
     * @return a 2D array representing the layer
     */
    public static StructurePiece[][] createLayer(Material material, int size) {
        StructurePiece[][] layer = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                layer[i][j] = new MaterialStructurePiece(material);
            }
        }
        return layer;
    }

    /**
     * Creates a square 2D layer of the specified item.
     *
     * @param item the item to fill the layer
     * @param size the size of the layer (size x size)
     * @return a 2D array representing the layer
     */
    public static StructurePiece[][] createLayer(IndustrialRevivalItem item, int size) {
        StructurePiece[][] layer = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                layer[i][j] = new IRBlockStructurePiece(item);
            }
        }
        return layer;
    }

    /**
     * Creates a row of the specified material.
     *
     * @param material the material to fill the row
     * @param size the size of the row
     * @return an array representing the row
     */
    public static StructurePiece[] createRow(Material material, int size) {
        StructurePiece[] row = new StructurePiece[size];
        for (int i = 0; i < size; i++) {
            row[i] = new MaterialStructurePiece(material);
        }
        return row;
    }

    /**
     * Creates a row of the specified item.
     *
     * @param item the item to fill the row
     * @param size the size of the row
     * @return an array representing the row
     */
    public static StructurePiece[] createRow(IndustrialRevivalItem item, int size) {
        StructurePiece[] row = new StructurePiece[size];
        for (int i = 0; i < size; i++) {
            row[i] = new IRBlockStructurePiece(item);
        }
        return row;
    }

    /**
     * Creates a square 2D outline (border) of the specified material.
     *
     * @param material the material for the outline
     * @param size the size of the outline (size x size)
     * @return a 2D array representing the outline
     */
    public static StructurePiece[][] createOutline(Material material, int size) {
        StructurePiece[][] outline = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    outline[i][j] = new MaterialStructurePiece(material);
                } else {
                    outline[i][j] = null;
                }
            }
        }
        return outline;
    }

    /**
     * Creates a square 2D outline (border) of the specified item.
     *
     * @param item the item for the outline
     * @param size the size of the outline (size x size)
     * @return a 2D array representing the outline
     */
    public static StructurePiece[][] createOutline(IndustrialRevivalItem item, int size) {
        StructurePiece[][] outline = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    outline[i][j] = new IRBlockStructurePiece(item);
                } else {
                    outline[i][j] = null;
                }
            }
        }
        return outline;
    }

    /**
     * Creates a square 2D hollow (empty border, filled center) of the specified material.
     *
     * @param material the material for the hollow center
     * @param size the size of the hollow (size x size)
     * @return a 2D array representing the hollow
     */
    public static StructurePiece[][] createHollow(Material material, int size) {
        StructurePiece[][] hollow = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    hollow[i][j] = null;
                } else {
                    hollow[i][j] = new MaterialStructurePiece(material);
                }
            }
        }
        return hollow;
    }

    /**
     * Creates a square 2D hollow (empty border, filled center) of the specified item.
     *
     * @param item the item for the hollow center
     * @param size the size of the hollow (size x size)
     * @return a 2D array representing the hollow
     */
    public static StructurePiece[][] createHollow(IndustrialRevivalItem item, int size) {
        StructurePiece[][] hollow = new StructurePiece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    hollow[i][j] = null;
                } else {
                    hollow[i][j] = new IRBlockStructurePiece(item);
                }
            }
        }
        return hollow;
    }

    /**
     * Creates a cube (3D array) of the specified material.
     *
     * @param material the material to fill the cube
     * @param size the size of the cube (size x size x size)
     * @return a 3D array representing the cube
     */
    public static StructurePiece[][][] createCube(Material material, int size) {
        StructurePiece[][][] cube = new StructurePiece[size][size][size];
        for (int i = 0; i < size; i++) {
            cube[i] = createLayer(material, size);
        }
        return cube;
    }

    /**
     * Creates a cube (3D array) of the specified item.
     *
     * @param item the item to fill the cube
     * @param size the size of the cube (size x size x size)
     * @return a 3D array representing the cube
     */
    public static StructurePiece[][][] createCube(IndustrialRevivalItem item, int size) {
        StructurePiece[][][] cube = new StructurePiece[size][size][size];
        for (int i = 0; i < size; i++) {
            cube[i] = createLayer(item, size);
        }
        return cube;
    }

    /**
     * Creates a 3D structure from a 3D array of materials.
     *
     * @param materials the 3D array of materials
     * @return a 3D array of structure pieces
     */
    public static StructurePiece[][][] createStructure(Material[][][] materials) {
        StructurePiece[][][] structure = new StructurePiece[materials.length][materials[0].length][materials[0][0].length];
        for (int i = 0; i < materials.length; i++) {
            for (int j = 0; j < materials[i].length; j++) {
                for (int k = 0; k < materials[i][j].length; k++) {
                    if (materials[i][j][k] != null) {
                        structure[i][j][k] = new MaterialStructurePiece(materials[i][j][k]);
                    } else {
                        structure[i][j][k] = new MaterialStructurePiece(Material.AIR);
                    }
                }
            }
        }
        return structure;
    }

    /**
     * Creates a structure piece from a material.
     *
     * @param material the material
     * @return a MaterialStructurePiece instance
     */
    public static StructurePiece material(Material material) {
        return new MaterialStructurePiece(material);
    }

    /**
     * Creates a structure piece from an IndustrialRevivalItem.
     *
     * @param item the item
     * @return an IRBlockStructurePiece instance
     */
    public static StructurePiece ir(IndustrialRevivalItem item) {
        return new IRBlockStructurePiece(item);
    }

    /**
     * Creates a structure piece that matches any block/item.
     *
     * @return an AnyStructurePiece instance
     */
    public static StructurePiece any() {
        return new AnyStructurePiece();
    }

    /**
     * Creates a structure piece representing air (empty space).
     *
     * @return a MaterialStructurePiece instance with Material.AIR
     */
    public static StructurePiece air() {
        return new MaterialStructurePiece(Material.AIR);
    }

    /**
     * Creates a section structure piece from the given pieces.
     *
     * @param pieces the structure pieces to include in the section
     * @return a SectionStructurePiece instance
     */
    public static StructurePiece section(StructurePiece... pieces) {
        return new SectionStructurePiece(pieces);
    }
}
