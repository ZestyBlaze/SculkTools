package dev.teamcitrus.sculktools.registry;

import dev.teamcitrus.sculktools.Pneumaphage;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pneumaphage.MODID);

    private static final DeferredHolder<CreativeModeTab, CreativeModeTab> BETTER_FARMS_TAB = CREATIVE_MODE_TABS.register("pneumaphage", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.pneumaphage"))
            .icon(() -> ItemRegistry.SCULK_PICKAXE.get().getDefaultInstance())
            .displayItems((parameters, output) -> ItemRegistry.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .build());
}
