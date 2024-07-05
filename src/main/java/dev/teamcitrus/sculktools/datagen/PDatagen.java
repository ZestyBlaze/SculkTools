package dev.teamcitrus.sculktools.datagen;

import dev.teamcitrus.citruslib.util.DatagenUtils;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.datagen.provider.GLMProvider;
import dev.teamcitrus.sculktools.datagen.provider.PItemModelProvider;
import dev.teamcitrus.sculktools.datagen.provider.lang.EnUsProvider;
import net.minecraft.DetectedVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Pneumaphage.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput output = gen.getPackOutput();

        gen.addProvider(event.includeServer(), new GLMProvider(output));

        gen.addProvider(event.includeClient(), new PItemModelProvider(output, helper));

        gen.addProvider(event.includeClient(), new EnUsProvider(output));

        DatagenUtils.makeMetadataFile(output, Pneumaphage.MODID);
    }
}
