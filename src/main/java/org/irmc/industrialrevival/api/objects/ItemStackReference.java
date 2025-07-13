package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
@Getter
public class ItemStackReference {
    private final ReferenceType referenceType;
    private ItemDictionary dictionary;
    private ItemStack itemStack;

    public ItemStackReference(@NotNull ItemStack itemStack) {
        this.referenceType = ReferenceType.ITEMSTACK;
        this.itemStack = new ItemStack(itemStack.getType(), itemStack.getAmount());
        this.itemStack.setItemMeta(itemStack.getItemMeta());
    }

    public ItemStackReference(@NotNull ItemDictionary dictionary) {
        this.referenceType = ReferenceType.DICTIONARY;
        this.dictionary = dictionary;
    }

    @NotNull
    public ItemStackReference of(@NotNull ItemStack itemStack) {
        return new ItemStackReference(itemStack);
    }

    @NotNull
    public ItemStackReference of(@NotNull ItemDictionary dictionary) {
        return new ItemStackReference(dictionary);
    }

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
     * @author balugaq
     */
    public enum ReferenceType {
        ITEMSTACK,
        DICTIONARY
    }
}
