package dev.teamcitrus.sculktools.datagen.provider;

import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.glm.BlazingLootModifier;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public class GLMProvider extends GlobalLootModifierProvider {
    public GLMProvider(PackOutput output) {
        super(output, Pneumaphage.MODID);
    }

    @Override
    protected void start() {
        add("blazing_trait_pick",
                new BlazingLootModifier(
                        new LootItemCondition[] {
                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemRegistry.SCULK_PICKAXE.get())).build(),
                        }
                )
        );
    }
}
