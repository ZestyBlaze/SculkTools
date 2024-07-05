package dev.teamcitrus.sculktools.registry;

import com.mojang.serialization.Codec;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.glm.BlazingLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifierRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Pneumaphage.MODID);
    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<BlazingLootModifier>> AUTO_SMELT = LOOT.register("blazing_smelting", BlazingLootModifier.CODEC);
}
