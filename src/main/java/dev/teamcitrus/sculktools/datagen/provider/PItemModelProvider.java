package dev.teamcitrus.sculktools.datagen.provider;

import dev.teamcitrus.citruslib.datagen.CitrusItemModelProvider;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PItemModelProvider extends CitrusItemModelProvider {
    public PItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Pneumaphage.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ItemRegistry.SCULK_SWORD.get());
        handheldItem(ItemRegistry.SCULK_PICKAXE.get());
    }
}
