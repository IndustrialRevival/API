/**
 * Interface for permission management operations.
 * Provides methods for checking permissions in different contexts.
 */
public interface IPermissionService {
    /**
     * Checks if a permissible has the specified permission.
     *
     * @param p          the permissible to check
     * @param permission the permission to check
     * @return true if the permissible has the permission, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Permission permission);

    /**
     * Checks if a permissible has the specified permission at a specific location.
     *
     * @param p          the permissible to check
     * @param location   the location to check the permission at
     * @param permission the permission to check
     * @return true if the permissible has the permission at the location, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, Location location, Permission permission);

    /**
     * Checks if a permissible has the specified permission for a particular item.
     *
     * @param p          the permissible to check
     * @param item       the item to check the permission for
     * @param permission the permission to check
     * @return true if the permissible has the permission for the item, false otherwise
     */
    @ParametersAreNonnullByDefault
    boolean hasPermission(Permissible p, IndustrialRevivalItem item, Permission permission);
}