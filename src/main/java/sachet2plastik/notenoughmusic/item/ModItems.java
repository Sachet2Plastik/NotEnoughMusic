package sachet2plastik.notenoughmusic.item;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sachet2plastik.notenoughmusic.DiscDefinition;
import sachet2plastik.notenoughmusic.DiscsRegistry;
import sachet2plastik.notenoughmusic.NotEnoughMusic;
import sachet2plastik.notenoughmusic.sound.ModSound;
import sachet2plastik.notenoughmusic.DiscsRegistry.Artists;

import java.util.Comparator;
import java.util.Map;


public class ModItems {

    public static final Item TEST_MUSIC_DISC;

    public static final RegistryKey<ItemGroup> CUSTOM_CREATIVE_TAB_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(NotEnoughMusic.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TEST_MUSIC_DISC))
            .displayName(Text.of("Not Enough Music"))
            .build();

    private static Item registerItem(String name, Item item) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, "discs/" + name);

        return Registry.register(Registries.ITEM, id, item);
    }

    private static Item registerByArtist(String name, Item item, Artists artist) {
        String formatedName = artist.prefix() + NotEnoughMusic.MOD_FORMAT_NAME
                      + "_" + artist.Name() + "_" + name + "_music_disc";
        return registerItem(formatedName, item);
    }

    public static void registerModItems() {
        NotEnoughMusic.LOGGER.info("Registering " + NotEnoughMusic.MOD_ID + " Items");

        DiscsRegistry.DISCS.forEach(((name, definition) -> {

            definition.Item = registerByArtist(name,
                    new Item(new Item.Settings()
                            .jukeboxPlayable(definition.Key)
                            .maxCount(definition.MaxCount)
                            .rarity(definition.Rarity)),
                    definition.Artist);

            DiscsRegistry.DISCS.put(name, definition);
        }));

        Registry.register(Registries.ITEM_GROUP, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_CREATIVE_TAB_KEY).register(entries -> {
            entries.add(TEST_MUSIC_DISC);

            DiscsRegistry.DISCS.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<String, DiscDefinition> e) -> e.getValue().Artist.Name())
                .thenComparing(e -> e.getValue().Name))
                .forEach(entry -> {
                    DiscDefinition def = entry.getValue();

                    entries.add(def.Item);
                });
        });
    }

    static {
        TEST_MUSIC_DISC = registerItem("test/nem_test_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TEST_KEY)
                                            .maxCount(1)
                                            .rarity(Rarity.COMMON)));
    }
}
