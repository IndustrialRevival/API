package org.irmc.industrialrevival.api.objects.events.interfaces;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

/**
 * Interface that relates an object to an IndustrialRevivalItem.
 * Used for events or objects that are associated with specific items.
 */
public interface RelatedIRItem {
    /**
     * Returns the IndustrialRevivalItem associated with this object.
     *
     * @return the IndustrialRevivalItem instance
     */
    IndustrialRevivalItem getIritem();
}