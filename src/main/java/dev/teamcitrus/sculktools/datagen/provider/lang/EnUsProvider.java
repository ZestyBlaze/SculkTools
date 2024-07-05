package dev.teamcitrus.sculktools.datagen.provider.lang;

import dev.teamcitrus.citruslib.util.StringUtils;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, Pneumaphage.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<DeferredHolder<Item, ? extends Item>> items = new HashSet<>(ItemRegistry.ITEMS.getEntries());

        add("item.pneumaphage.hold_shift", "Hold SHIFT for more info");
        add("item.pneumaphage.no_traits", "No Traits Applied");
        add("itemGroup.pneumaphage", "Pseumaphage");
        add("trait.pneumaphage.blazing", "Blazing");
        add("trait.pneumaphage.blazing.icon", "\uD83D\uDD25");
        add("trait.pneumaphage.blazing.info", "Auto-smelts mined ores");
        add("trait.pneumaphage.silky", "Silky");
        add("trait.pneumaphage.silky.icon", "\uD83E\uDEB6");
        add("trait.pneumaphage.silky.info", "Silky soft touch when mining");

        items.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("item\\.pneumaphage\\.", "");
            name = StringUtils.toTitleCase(name, "_");
            add(i.get().getDescriptionId(), name);
        });
    }
}
