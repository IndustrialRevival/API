package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.text.MessageFormat;

/**
 * Represents a position within a Minecraft chunk.
 * <p>
 * This class provides a convenient way to work with chunk coordinates and positions.
 * It encapsulates both the chunk object and its coordinates, providing methods for
 * comparison and human-readable representation.
 * </p>
 * <p>
 * The class uses the chunk's hash code for efficient equality comparisons and
 * provides a human-readable format for debugging and logging purposes.
 * </p>
 *
 * @author balugaq
 * @see Chunk
 * @see Location
 */
@SuppressWarnings("unused")
@Getter
public class ChunkPosition {
    private final Chunk chunk;
    private final int chunkX;
    private final int chunkZ;
    private final int hash;

    /**
     * Creates a new ChunkPosition from a location.
     *
     * @param location the location to create the chunk position from
     */
    public ChunkPosition(Location location) {
        this(location.getChunk());
    }

    /**
     * Creates a new ChunkPosition from a chunk.
     *
     * @param chunk the chunk to create the position from
     */
    public ChunkPosition(Chunk chunk) {
        this.chunk = chunk;
        this.chunkX = chunk.getX();
        this.chunkZ = chunk.getZ();
        this.hash = chunk.hashCode();
    }

    /**
     * Compares this ChunkPosition with another object for equality.
     * <p>
     * Two ChunkPosition objects are considered equal if they represent the same chunk
     * in the same world. The comparison first checks the hash code for efficiency,
     * then falls back to comparing coordinates and world if necessary.
     * </p>
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ChunkPosition other = (ChunkPosition) obj;
        if (this.hash == other.hash) {
            return true;
        }

        return this.chunkX == other.chunkX && this.chunkZ == other.chunkZ && this.chunk.getWorld().equals(other.chunk.getWorld());
    }

    /**
     * Returns a human-readable string representation of this chunk position.
     * <p>
     * The format is: "WorldName(chunkX, chunkZ)"
     * </p>
     *
     * @return a human-readable string representation of this chunk position
     */
    public String humanize() {
        return MessageFormat.format("{0}({1}, {2})", this.getChunk().getWorld().getName(), this.chunkX, this.chunkZ);
    }
}
