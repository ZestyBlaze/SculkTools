package dev.teamcitrus.sculktools.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;

import java.util.List;

public record Traits(
        TextColor color, List<Item> allowedItems, int maxLevel
) implements ITrait {
    public static final Codec<Traits> CODEC = RecordCodecBuilder.create(func -> func.group(
            TextColor.CODEC.fieldOf("color").forGetter(Traits::color),
            BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("allowedItems").forGetter(Traits::allowedItems),
            ExtraCodecs.strictOptionalField(Codec.INT, "maxLevel", 1).forGetter(Traits::maxLevel)
    ).apply(func, Traits::new));

    @Override
    public Codec<? extends ITrait> getCodec() {
        return CODEC;
    }
}
