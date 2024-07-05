package dev.teamcitrus.sculktools.glm;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.registry.ItemRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.Optional;
import java.util.function.Supplier;

public class BlazingLootModifier extends LootModifier {
    public static final Supplier<Codec<BlazingLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, BlazingLootModifier::new))
    );

    public BlazingLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
        Entity entity = context.getParam(LootContextParams.THIS_ENTITY);
        BlockState state = context.getParam(LootContextParams.BLOCK_STATE);
        if (!state.is(Tags.Blocks.ORES)) return generatedLoot;

        if (entity instanceof Player player) {
            ItemStack heldItem = player.getItemInHand(player.getUsedItemHand());
            if (heldItem.is(ItemRegistry.SCULK_PICKAXE.get())) {
                ListTag list = heldItem.getOrCreateTagElement(Pneumaphage.MODID).getList("Traits", Tag.TAG_STRING);

                if (list.getAsString().contains("pneumaphage:blazing")) {
                    generatedLoot.forEach((stack) -> {
                        Optional<RecipeHolder<SmeltingRecipe>> optional = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel());
                        if (optional.isPresent()) {
                            ItemStack smeltedItemStack = optional.get().value().getResultItem(context.getLevel().registryAccess());
                            if (!smeltedItemStack.isEmpty()) {
                                ItemStack copy = ItemHandlerHelper.copyStackWithSize(smeltedItemStack, stack.getCount() * smeltedItemStack.getCount());
                                newLoot.add(copy);
                            } else {
                                newLoot.add(stack);
                            }
                        } else {
                            newLoot.add(stack);
                        }
                    });
                }
            }
        }
        return newLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
