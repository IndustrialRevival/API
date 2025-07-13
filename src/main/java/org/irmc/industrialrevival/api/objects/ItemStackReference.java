package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a reference to an item stack or item dictionary.
 * <p>
 * This class provides a flexible way to reference items in the IndustrialRevival system.
 * It can reference either a specific ItemStack or an ItemDictionary, allowing for
 * both exact item matching and dictionary-based matching.
 * </p>
 * <p>
 * The class supports two types of references:
 * <ul>
 *   <li>ITEMSTACK: References a specific item stack with exact matching</li>
 *   <li>DICTIONARY: References an item dictionary for flexible matching</li>
 * </ul>
 * </p>
 *
 * @author balugaq
 * @see ItemStack
 * @see ItemDictionary
 */
@Getter
public class ItemStackReference {
    private final ReferenceType referenceType;
    private ItemDictionary dictionary;
    private ItemStack itemStack;

    /**
     * Creates a new ItemStackReference from an ItemStack.
     * <p>
     * This constructor creates a reference that will match against the exact item stack
     * provided, including its metadata.
     * </p>
     *
     * @param itemStack the item stack to reference
     */
    public ItemStackReference(@NotNull ItemStack itemStack) {
        this.referenceType = ReferenceType.ITEMSTACK;
        this.itemStack = new ItemStack(itemStack.getType(), itemStack.getAmount());
        this.itemStack.setItemMeta(itemStack.getItemMeta());
    }

    /**
     * Creates a new ItemStackReference from an ItemDictionary.
     * <p>
     * This constructor creates a reference that will match against any item
     * contained within the specified dictionary.
     * </p>
     *
     * @param dictionary the item dictionary to reference
     */
    public ItemStackReference(@NotNull ItemDictionary dictionary) {
        this.referenceType = ReferenceType.DICTIONARY;
        this.dictionary = dictionary;
    }

    /**
     * Creates a new ItemStackReference from an ItemStack.
     * <p>
     * This is a convenience factory method for creating ItemStack references.
     * </p>
     *
     * @param itemStack the item stack to reference
     * @return a new ItemStackReference for the given item stack
     */
    @NotNull
    public ItemStackReference of(@NotNull ItemStack itemStack) {
        return new ItemStackReference(itemStack);
    }

    /**
     * Creates a new ItemStackReference from an ItemDictionary.
     * <p>
     * This is a convenience factory method for creating dictionary references.
     * </p>
     *
     * @param dictionary the item dictionary to reference
     * @return a new ItemStackReference for the given dictionary
     */
    @NotNull
    public ItemStackReference of(@NotNull ItemDictionary dictionary) {
        return new ItemStackReference(dictionary);
    }

    /**
     * Checks if the given item stack matches this reference.
     * <p>
     * For ITEMSTACK references, this performs an exact item similarity check.
     * For DICTIONARY references, this checks if the item is contained within the dictionary.
     * </p>
     *
     * @param incomingItemStack the item stack to check against this reference
     * @return true if the item stack matches this reference, false otherwise
     */
    public boolean itemsMatch(@NotNull ItemStack incomingItemStack) {
        if (this.referenceType == ReferenceType.ITEMSTACK) {
            return ItemUtils.isItemSimilar(this.itemStack, incomingItemStack);
        }

        if (this.referenceType == ReferenceType.DICTIONARY) {
            if (incomingItemStack == null || incomingItemStack.getType() == Material.AIR) {
                return false;
            }

            return dictionary.isInDictionary(incomingItemStack);
        }

        return false;
    }

    /**
     * Enum representing the type of reference this ItemStackReference represents.
     *
     * @author balugaq
     */
    public enum ReferenceType {
        /** References a specific item stack with exact matching */
        ITEMSTACK,
        /** References an item dictionary for flexible matching */
        DICTIONARY
    }
}
