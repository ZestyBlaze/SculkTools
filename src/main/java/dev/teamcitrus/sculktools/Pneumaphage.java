package dev.teamcitrus.sculktools;

import dev.teamcitrus.sculktools.data.TraitRegistry;
import dev.teamcitrus.sculktools.registry.CreativeModeTabRegistry;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import dev.teamcitrus.sculktools.registry.LootModifierRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Pneumaphage.MODID)
public class Pneumaphage {
    public static final String MODID = "pneumaphage";
    public static final Logger LOGGER = LogManager.getLogger();

    public Pneumaphage(IEventBus bus) {
        CreativeModeTabRegistry.CREATIVE_MODE_TABS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        LootModifierRegistry.LOOT.register(bus);
        bus.register(this);
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MODID, name);
    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        TraitRegistry.INSTANCE.registerToBus();
    }
}
