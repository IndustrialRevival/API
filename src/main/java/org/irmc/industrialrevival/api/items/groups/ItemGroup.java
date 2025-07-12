package org.irmc.industrialrevival.api.items.groups;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.Displayable;
import org.irmc.industrialrevival.api.menu.gui.NormalGroupMenu;
import org.irmc.industrialrevival.api.menu.gui.PageableMenu;
import org.irmc.industrialrevival.dock.IRDock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * An ItemGroup is a collection of items that can be displayed in a guide menu.
 *
 * @author lijinhong11
 * @author balugaq
 * @see NormalItemGroup
 * @see NestedItemGroup
 */
public abstract class ItemGroup implements Displayable<ItemGroup> {
    @Getter
    private final NamespacedKey key;
    @Getter
    private final ItemStack icon;

    private final List<IndustrialRevivalItem> items = new LinkedList<>();
    @Getter
    private final Function<Player, NormalGroupMenu> menuGenerator;
    boolean locked = false;
    @Getter
    private int tier;
    @Getter
    private boolean onlyVisibleByAdmins = false;

    /**
     * Creates a new ItemGroup with the given key and icon.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     */
    protected ItemGroup(@NotNull Function<Player, NormalGroupMenu> menuGenerator, @NotNull NamespacedKey key, @NotNull ItemStack icon) {
        this(menuGenerator, key, icon, 3);
    }

    /**
     * Creates a new ItemGroup with the given key, icon, and tier.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     * @param tier the tier of the item group, default is 3
     * @apiNote the lower the tier is, the higher the priority of the item group is
     */
    protected ItemGroup(@Nullable Function<Player, NormalGroupMenu> menuGenerator, @NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        this.menuGenerator = menuGenerator == null ? p -> new NormalGroupMenu(p, this) : menuGenerator;
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    /**
     * Creates a new ItemGroup with the given key and icon.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     */
    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon) {
        this(key, icon, 3);
    }

    /**
     * Creates a new ItemGroup with the given key, icon, and tier.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     * @param tier the tier of the item group, default is 3
     * @apiNote the lower the tier is, the higher the priority of the item group is
     */
    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        this(null, key, icon, tier);
    }

    /**
     * Checks if the given item is allowed to be added to this item group.
     *
     * @param item the item to be added
     * @return true if the item is allowed to be added, false otherwise
     */
    public boolean allowedItem(@NotNull IndustrialRevivalItem item) {
        return true;
    }

    /**
     * Opens the guide menu for this item group.
     *
     * @param p    the player who opened the guide menu
     * @param page the page number of the guide menu to be opened
     */
    public abstract void onOpen(@NotNull Player p, int page);

    /**
     * Returns a list of all the items in this item group.
     *
     * @return a list of all the items in this item group
     */
    @NotNull
    public List<IndustrialRevivalItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Adds an item to this item group.
     *
     * @param item the item to be added
     */
    public void addItem(@NotNull IndustrialRevivalItem item) {
        if (allowedItem(item)) {
            this.items.add(item);
        } else {
            // not allowed item
            IRDock.getPlugin().getLogger().warning(MessageFormat.format(
                    "Item {0} (From addon {1}) is not allowed to be added to group {2} (From addon {3})",
                    item.getItemName(),
                    item.getAddon().getPlugin().getName(),
                    this.getClass().getSimpleName(),
                    this.getKey().getKey()));
        }
    }

    /**
     * Registers this item group.
     *
     * @throws IllegalStateException if the item group is already registered
     */
    public void register() {
        if (isRegistered()) {
            throw new IllegalStateException("the item group is already registered");
        }

        locked = true;

        IRDock.getPlugin().getRegistry().registerItemGroup(this);
    }

    /**
     * Checks if this item group is registered.
     *
     * @return true if the item group is registered, false otherwise
     */
    public final boolean isRegistered() {
        return IRDock.getPlugin().getRegistry().getItemGroups().containsKey(key);
    }

    /**
     * Sets the tier of this item group.
     *
     * @param tier the tier of this item group
     */
    public void setTier(int tier) {
        checkLocked();
        this.tier = tier;
    }

    /**
     * Sets if this item group is only visible by admins.
     *
     * @param onlyVisibleByAdmins if this item group is only visible by admins
     * @apiNote if true, players without the admin permission will not see this item group in the guide menu
     */
    public void setOnlyVisibleByAdmins(boolean onlyVisibleByAdmins) {
        checkLocked();
        this.onlyVisibleByAdmins = onlyVisibleByAdmins;
    }

    /**
     * Checks if the item group is locked.
     */
    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException("the item group is locked");
        }
    }

    @Override
    public ItemStack getDisplayItem(ItemGroup itemGroup) {
        return PageableMenu.getDisplayItem0(itemGroup);
    }
}
