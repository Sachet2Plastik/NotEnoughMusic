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
import sachet2plastik.notenoughmusic.NotEnoughMusic;
import sachet2plastik.notenoughmusic.sound.ModSound;
import sachet2plastik.notenoughmusic.NotEnoughMusic.Artists;


public class ModItems {

    public static final Item TEST_MUSIC_DISC;

    // T_en_M discs
    public static final Item NEM_TENM_UNDEAD_MUSIC_DISC;
    public static final Item NEM_TENM_FROSTED_MUSIC_DISC;
    public static final Item NEM_TENM_SOULLESS_MUSIC_DISC;

    // Laudividni discs
    public static final Item NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC;

    // Stereonyx discs
    public static final Item NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC;

    public static final RegistryKey<ItemGroup> CUSTOM_CREATIVE_TAB_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(NotEnoughMusic.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.NEM_TENM_FROSTED_MUSIC_DISC))
            .displayName(Text.of("Not Enough Music"))
            .build();

    private static Item registerItem(String name, Item item) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, "discs/" + name);

        return Registry.register(Registries.ITEM, id, item);
    }

    private static Item registerByArtist(String name, Item item, Artists artist) {
        return registerItem(artist.prefix() + name, item);
    }

    public static void registerModItems() {
        NotEnoughMusic.LOGGER.info("Registering " + NotEnoughMusic.MOD_ID + " Items");

        Registry.register(Registries.ITEM_GROUP, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_CREATIVE_TAB_KEY).register(entries -> {
            entries.add(TEST_MUSIC_DISC);

            // T_en_M discs
            entries.add(NEM_TENM_UNDEAD_MUSIC_DISC);
            entries.add(NEM_TENM_FROSTED_MUSIC_DISC);
            entries.add(NEM_TENM_SOULLESS_MUSIC_DISC);

            // Laudividni discs
            entries.add(NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC);

            // Stereonyx discs
            entries.add(NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC);
        });
    }

    static {
        TEST_MUSIC_DISC = registerItem("test/nem_test_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TEST_KEY)
                                            .maxCount(1)
                                            .rarity(Rarity.COMMON)));

        // T_en_M discs
        NEM_TENM_UNDEAD_MUSIC_DISC = registerByArtist("nem_tenm_undead_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_UNDEAD_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_FROSTED_MUSIC_DISC = registerByArtist("nem_tenm_frosted_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_FROSTED_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_SOULLESS_MUSIC_DISC = registerByArtist("nem_tenm_soulless_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_SOULLESS_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        // Laudividni discs

        NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC = registerByArtist("nem_laudividni_nightfall_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_LAUDIVIDNI_NIGHTFALL_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.LAUDIVIDNI);

        // Stereonyx discs

        NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC = registerByArtist("nem_stereonyx_dragonfight_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_STEREONYX_DRAGONFIGHT_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.STEREONYX);
    }
}
