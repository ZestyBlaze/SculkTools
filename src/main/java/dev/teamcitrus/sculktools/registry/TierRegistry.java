package dev.teamcitrus.sculktools.registry;

import dev.teamcitrus.sculktools.Pneumaphage;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.TierSortingRegistry;

import java.util.List;

public class TierRegistry {
    public static final Tier SCULK = TierSortingRegistry.registerTier(
            new SimpleTier(0, 250, 2.0f, 0.0f, 0,
                    BlockTags.create(Pneumaphage.id("needs_sculk_tools")),
                    () -> Ingredient.of(Items.ECHO_SHARD)
            ),
            Pneumaphage.id("sculk"),
            List.of(),
            List.of(Tiers.WOOD)
    );
}
