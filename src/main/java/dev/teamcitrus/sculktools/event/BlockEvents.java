package dev.teamcitrus.sculktools.event;

import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = Pneumaphage.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockEvents {
    @SubscribeEvent
    private static void blockBreakSpeed(PlayerEvent.BreakSpeed event) {
        ItemStack stack = event.getEntity().getItemInHand(event.getEntity().getUsedItemHand());
        if (stack.is(ItemRegistry.SCULK_PICKAXE.asItem())) {
            if (stack.hasTag()) {
                CompoundTag nbt = stack.getOrCreateTagElement(Pneumaphage.MODID);
                ListTag listTag = nbt.getList("Traits", Tag.TAG_STRING);
                if (listTag.getAsString().contains("pneumaphage:mining_1")) {
                    event.setNewSpeed(Tiers.STONE.getSpeed());
                }
            }
        }
    }
}
