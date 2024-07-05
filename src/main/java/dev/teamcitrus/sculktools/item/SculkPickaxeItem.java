package dev.teamcitrus.sculktools.item;

import dev.teamcitrus.citruslib.reload.DynamicHolder;
import dev.teamcitrus.sculktools.Pneumaphage;
import dev.teamcitrus.sculktools.data.TraitRegistry;
import dev.teamcitrus.sculktools.data.Traits;
import dev.teamcitrus.sculktools.registry.TierRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SculkPickaxeItem extends PickaxeItem {
    public SculkPickaxeItem() {
        super(TierRegistry.SCULK,
                -1,
                -2.8f,
                new Properties()
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTagElement(Pneumaphage.MODID);

            DynamicHolder<Traits> blazing = TraitRegistry.getTrait(Pneumaphage.id("blazing"));
            DynamicHolder<Traits> silky = TraitRegistry.getTrait(Pneumaphage.id("silky"));

            CompoundTag traits = new CompoundTag();
            traits.putInt(blazing.getId().toString(), blazing.get().maxLevel());
            traits.putInt(silky.getId().toString(), silky.get().maxLevel());

            tag.put("Traits", traits);

            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            CompoundTag traits = (CompoundTag) pStack.getOrCreateTagElement(Pneumaphage.MODID).get("Traits");

            if (traits == null || traits.isEmpty()) pTooltipComponents.add(Component.translatable("item.pneumaphage.no_traits").withStyle(ChatFormatting.GRAY));

            if (!(traits == null)) {
                traits.getAllKeys().forEach(s -> {
                    DynamicHolder<Traits> trait = TraitRegistry.getTrait(ResourceLocation.tryParse(s));
                    ResourceLocation traitReLoc = trait.getId();
                    String namespace = traitReLoc.getNamespace();
                    String path = traitReLoc.getPath();

                    pTooltipComponents.add(Component.translatable("trait." + namespace + "." + path + ".icon")
                            .withStyle(Style.EMPTY.withColor(trait.get().color()))
                            .append(" ")
                            .append(Component.translatable("trait." + namespace + "." + path)
                                    .withStyle(ChatFormatting.WHITE))
                            .append(" ")
                            .append(Component.translatable("enchantment.level." + traits.getInt(s)).withStyle(ChatFormatting.WHITE))
                    );

                    if (Screen.hasAltDown()) {
                        pTooltipComponents.add(Component.literal(" -> ").append(Component.translatable("trait." + namespace + "." + path + ".info").withStyle(ChatFormatting.GRAY)));
                    }
                });
            }
        } else {
            CompoundTag traits = (CompoundTag) pStack.getOrCreateTagElement(Pneumaphage.MODID).get("Traits");
            MutableComponent component = Component.empty();

            if (!(traits == null)) {
                traits.getAllKeys().forEach(s -> {
                    DynamicHolder<Traits> trait = TraitRegistry.getTrait(ResourceLocation.tryParse(s));
                    if (trait == null) return;

                    component.append(Component.translatable("trait." + trait.getId().getNamespace() + "." + trait.getId().getPath() + ".icon").setStyle(Style.EMPTY.withColor(trait.get().color())).append(" "));
                });

                if (!traits.isEmpty()) pTooltipComponents.add(component);
            }

            pTooltipComponents.add(Component.translatable("item.pneumaphage.hold_shift").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
