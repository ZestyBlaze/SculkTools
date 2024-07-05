package dev.teamcitrus.sculktools.data;

import dev.teamcitrus.citruslib.codec.CodecProvider;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;

import java.util.List;

public interface ITrait extends CodecProvider<ITrait> {
    TextColor color();

    List<Item> allowedItems();

    int maxLevel();
}
