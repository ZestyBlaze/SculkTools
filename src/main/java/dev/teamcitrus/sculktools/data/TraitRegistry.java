package dev.teamcitrus.sculktools.data;

import dev.teamcitrus.citruslib.reload.DynamicHolder;
import dev.teamcitrus.citruslib.reload.DynamicRegistry;
import dev.teamcitrus.sculktools.Pneumaphage;
import net.minecraft.resources.ResourceLocation;

public class TraitRegistry extends DynamicRegistry<ITrait> {
    public static final TraitRegistry INSTANCE = new TraitRegistry();

    private TraitRegistry() {
        super(Pneumaphage.LOGGER, "test/trait", true, true);
    }

    @Override
    protected void registerBuiltinCodecs() {
        registerDefaultCodec(Pneumaphage.id("trait"), Traits.CODEC);
    }

    public static DynamicHolder<Traits> getTrait(ResourceLocation traitID) {
        return INSTANCE.holder(traitID);
    }
}
