package dev.teamcitrus.sculktools.registry;

import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.item.SculkPickaxeItem;
import dev.teamcitrus.sculktools.item.SculkSwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Pneumaphage.MODID);

    public static final DeferredItem<SculkSwordItem> SCULK_SWORD = ITEMS.register("sculk_sword", SculkSwordItem::new);
    public static final DeferredItem<SculkPickaxeItem> SCULK_PICKAXE = ITEMS.register("sculk_pickaxe", SculkPickaxeItem::new);
}
