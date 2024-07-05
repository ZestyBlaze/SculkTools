package dev.teamcitrus.sculktools.item;

import dev.teamcitrus.sculktools.registry.TierRegistry;
import net.minecraft.world.item.SwordItem;

public class SculkSwordItem extends SwordItem {
    public SculkSwordItem() {
        super(TierRegistry.SCULK,
                3,
                -2.4f,
                new Properties()
        );
    }
}
