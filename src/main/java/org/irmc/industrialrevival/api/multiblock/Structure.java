package org.irmc.industrialrevival.api.multiblock;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.multiblock.piece.IRBlockStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.MaterialStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a multi-block structure in the IndustrialRevival system.
 * <p>
 * This class encapsulates the definition, validation, and manipulation of complex structures
 * composed of multiple blocks or items arranged in a 3D grid. It supports validation with rotation,
 * center specification, and conversion from materials or items.
 * </p>
 *
 * @author balugaq
 */
@SuppressWarnings("unused")
public class Structure {
    private @Getter
    static final Rotation[] ROTATIONS = new Rotation[]{Rotation.NONE, Rotation.CLOCKWISE, Rotation.FLIPPED, Rotation.COUNTER_CLOCKWISE};
    private @Getter
    final StructurePiece[][][] structure;
    private @Getter
    final int[] center;
    private @Getter
    final int[] size;

    /**
     * Constructs a multi-block structure with the specified pieces and center.
     *
     * @param structure all the pieces of the structure (3D array)
     * @param center    the center of the structure (array of 3 coordinates)
     */
    public Structure(@NotNull StructurePiece[][][] structure, int[] center) {
        Preconditions.checkArgument(structure != null, "Structure cannot be null");
        Preconditions.checkArgument(structure.length > 0, "Structure must have at least one layer");
        Preconditions.checkArgument(structure[0].length > 0, "Structure must have at least one row");
        Preconditions.checkArgument(structure[0][0].length > 0, "Structure must have at least one column");
        Preconditions.checkArgument(center != null, "Center cannot be null");
        Preconditions.checkArgument(center.length == 3, "Center must have 3 dimensions");
        Preconditions.checkArgument(center[0] >= 0 && center[0] < structure.length, "Center[0] (layer) is out of bounds");
        Preconditions.checkArgument(center[1] >= 0 && center[1] < structure[0].length, "Center[1] (row) is out of bounds");
        Preconditions.checkArgument(center[2] >= 0 && center[2] < structure[0][0].length, "Center[2] (column) is out of bounds");

        // if any null in structure, replace with air material
        for (int x = 0; x < structure.length; x++) {
            for (int y = 0; y < structure[x].length; y++) {
                for (int z = 0; z < structure[x][y].length; z++) {
                    if (structure[x][y][z] == null) {
                        structure[x][y][z] = new MaterialStructurePiece(Material.AIR);
                    }
                }
            }
        }

        this.structure = structure;
        this.center = center;
        this.size = new int[]{structure.length, structure[0].length, structure[0][0].length};
    }

    /**
     * Constructs a multi-block structure with the specified pieces and default center (0,0,0).
     *
     * @param structure all the pieces of the structure (3D array)
     */
    public Structure(@NotNull StructurePiece[][][] structure) {
        this(structure, new int[]{0, 0, 0});
    }

    /**
     * Creates a structure from a 3D array of materials.
     *
     * @param materials all {@link Material} of the structure
     * @return the created structure
     */
    public static Structure of(@NotNull Material[][][] materials) {
        return of(materials, new int[]{0, 0, 0});
    }

    /**
     * Creates a structure from a 3D array of materials and a specified center.
     *
     * @param materials all {@link Material} of the structure
     * @param center    the center of the structure
     * @return the created structure
     */
    public static Structure of(@NotNull Material[][][] materials, int[] center) {
        final StructurePiece[][][] structure = new StructurePiece[materials.length][materials[0].length][materials[0][0].length];
        for (int x = 0; x < materials.length; x++) {
            for (int y = 0; y < materials[x].length; y++) {
                for (int z = 0; z < materials[x][y].length; z++) {
                    structure[x][y][z] = new MaterialStructurePiece(materials[x][y][z]);
                }
            }
        }
        return new Structure(structure, center);
    }

    /**
     * Creates a structure from a 3D array of items.
     *
     * @param items all {@link IndustrialRevivalItem} of the structure
     * @return the created structure
     */
    public static Structure of(@NotNull IndustrialRevivalItem[][][] items) {
        return of(items, new int[]{0, 0, 0});
    }

    /**
     * Creates a structure from a 3D array of items and a specified center.
     *
     * @param items  all {@link IndustrialRevivalItem} of the structure
     * @param center the center of the structure
     * @return the created structure
     */
    public static Structure of(@NotNull IndustrialRevivalItem[][][] items, int[] center) {
        final StructurePiece[][][] structure = new StructurePiece[items.length][items[0].length][items[0][0].length];
        for (int x = 0; x < items.length; x++) {
            for (int y = 0; y < items[x].length; y++) {
                for (int z = 0; z < items[x][y].length; z++) {
                    structure[x][y][z] = new IRBlockStructurePiece(items[x][y][z]);
                }
            }
        }
        return new Structure(structure, center);
    }

    /**
     * Gets the centerpiece of the structure.
     *
     * @return the centerpiece of the structure
     */
    @NotNull
    public StructurePiece getCenterPiece() {
        return structure[center[0]][center[1]][center[2]];
    }

    /**
     * Checks if the structure at the given location is valid (matches this structure definition).
     * Will try all possible rotations.
     *
     * @param center the center of the structure
     * @return true if the structure is valid, false otherwise
     */
    public boolean isValid(@NotNull Location center) {
        for (Rotation rotation : ROTATIONS) {
            if (validateRotation(center, rotation)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the structure at the given location is valid for a specific rotation.
     *
     * @param center   the center of the structure
     * @param rotation the rotation to check
     * @return true if the structure is valid for the rotation, false otherwise
     */
    public boolean validateRotation(@NotNull Location center, @NotNull Rotation rotation) {
        for (int layer = 0; layer < structure.length; layer++) {
            if (validateLayer(center, layer, rotation) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the incorrect location of the structure for a specific rotation.
     *
     * @param center   the center of the structure
     * @param rotation the rotation to check
     * @return the incorrect location of the structure for the rotation, or null if the structure is valid
     */
    public String getIncorrect(@NotNull Location center, @NotNull Rotation rotation) {
        for (int layer = 0; layer < structure.length; layer++) {
            Location location = validateLayer(center, layer, rotation);
            if (location != null) {
                return location.toVector().toString();
            }
        }
        return null;
    }

    /**
     * Validates a specific layer of the structure for a specific rotation.
     *
     * @param centerLocation the center of the structure
     * @param layerLevel     the layer to check
     * @param rotation       the rotation to check
     * @return the incorrect location of the structure for the rotation, or null if the layer is valid
     */
    @Nullable
    private Location validateLayer(@NotNull Location centerLocation, int layerLevel, @NotNull Rotation rotation) {
        final StructurePiece[][] layer = structure[layerLevel];
        for (int z = 0; z < layer.length; z++) {
            for (int x = 0; x < layer[z].length; x++) {
                int dy = layerLevel - center[0];
                int dz = z - center[1];
                int dx = x - center[2];
                int temp;
                switch (rotation) {
                    case CLOCKWISE -> {
                        temp = dx;
                        dx = -dz;
                        dz = temp;
                    }
                    case FLIPPED -> {
                        dx = -dx;
                        dz = -dz;
                    }
                    case COUNTER_CLOCKWISE -> {
                        temp = dx;
                        dx = dz;
                        dz = -temp;
                    }
                }
                final Location location = centerLocation.clone().add(dx, dy, dz);
                if (!layer[z][x].matches(location.getBlock())) {
                    return location;
                }
            }
        }
        return null;
    }
}
