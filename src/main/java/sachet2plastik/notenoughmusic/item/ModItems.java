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
import sachet2plastik.notenoughmusic.DiscsRegistry;
import sachet2plastik.notenoughmusic.NotEnoughMusic;
import sachet2plastik.notenoughmusic.sound.ModSound;
import sachet2plastik.notenoughmusic.DiscsRegistry.Artists;


public class ModItems {

    public static final Item TEST_MUSIC_DISC;

    // T_en_M discs
    /*
    public static final Item NEM_TENM_UNDEAD_MUSIC_DISC;
    public static final Item NEM_TENM_FROSTED_MUSIC_DISC;
    public static final Item NEM_TENM_SOULLESS_MUSIC_DISC;
    public static final Item NEM_TENM_VOYAGE_MUSIC_DISC;
    public static final Item NEM_TENM_BREEZE_MUSIC_DISC;
    public static final Item NEM_TENM_MINESHAFT_MUSIC_DISC;
    public static final Item NEM_TENM_BELOW_MUSIC_DISC;
    public static final Item NEM_TENM_NOESCAPE_MUSIC_DISC;
    public static final Item NEM_TENM_GILDED_MUSIC_DISC;
    public static final Item NEM_TENM_TUNDRA_MUSIC_DISC;
    public static final Item NEM_TENM_SHULKER_MUSIC_DISC;
    public static final Item NEM_TENM_PUMPKIN_MUSIC_DISC;
    public static final Item NEM_TENM_GUARDIAN_MUSIC_DISC;
    public static final Item NEM_TENM_5_10KSPECIAL_MUSIC_DISC;
    public static final Item NEM_TENM_THEENDERDRAGON_MUSIC_DISC;
    public static final Item NEM_TENM_RAIDERS_MUSIC_DISC;
    public static final Item NEM_TENM_THEWITHER_MUSIC_DISC;
    public static final Item NEM_TENM_THUNDERSTORM_MUSIC_DISC;
    public static final Item NEM_TENM_1KSPECIAL_MUSIC_DISC;

    // Laudividni discs
    public static final Item NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC;

    // Stereonyx discs
    public static final Item NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC;*/

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

            // T_en_M discs
            DiscsRegistry.DISCS.forEach(((name, definition) -> {
                entries.add(definition.Item);
            }));
            /*
            entries.add(NEM_TENM_UNDEAD_MUSIC_DISC);
            entries.add(NEM_TENM_FROSTED_MUSIC_DISC);
            entries.add(NEM_TENM_SOULLESS_MUSIC_DISC);
            entries.add(NEM_TENM_VOYAGE_MUSIC_DISC);
            entries.add(NEM_TENM_BREEZE_MUSIC_DISC);
            entries.add(NEM_TENM_MINESHAFT_MUSIC_DISC);
            entries.add(NEM_TENM_BELOW_MUSIC_DISC);
            entries.add(NEM_TENM_NOESCAPE_MUSIC_DISC);
            entries.add(NEM_TENM_GILDED_MUSIC_DISC);
            entries.add(NEM_TENM_TUNDRA_MUSIC_DISC);
            entries.add(NEM_TENM_SHULKER_MUSIC_DISC);
            entries.add(NEM_TENM_PUMPKIN_MUSIC_DISC);
            entries.add(NEM_TENM_GUARDIAN_MUSIC_DISC);
            entries.add(NEM_TENM_5_10KSPECIAL_MUSIC_DISC);
            entries.add(NEM_TENM_THEENDERDRAGON_MUSIC_DISC);
            entries.add(NEM_TENM_RAIDERS_MUSIC_DISC);
            entries.add(NEM_TENM_THEWITHER_MUSIC_DISC);
            entries.add(NEM_TENM_THUNDERSTORM_MUSIC_DISC);
            entries.add(NEM_TENM_1KSPECIAL_MUSIC_DISC);

            // Laudividni discs
            entries.add(NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC);

            // Stereonyx discs
            entries.add(NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC);*/
        });
    }

    static {
        TEST_MUSIC_DISC = registerItem("test/nem_test_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TEST_KEY)
                                            .maxCount(1)
                                            .rarity(Rarity.COMMON)));

        // T_en_M discs
        /*NEM_TENM_UNDEAD_MUSIC_DISC = registerByArtist("undead",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_UNDEAD_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_FROSTED_MUSIC_DISC = registerByArtist("frosted",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_FROSTED_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_SOULLESS_MUSIC_DISC = registerByArtist("soulless",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_SOULLESS_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_VOYAGE_MUSIC_DISC = registerByArtist("voyage",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_VOYAGE_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_BREEZE_MUSIC_DISC = registerByArtist("breeze",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_BREEZE_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_MINESHAFT_MUSIC_DISC = registerByArtist("mineshaft",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_MINESHAFT_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_BELOW_MUSIC_DISC = registerByArtist("below",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_BELOW_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_NOESCAPE_MUSIC_DISC = registerByArtist("noescape",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_NOESCAPE_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_GILDED_MUSIC_DISC = registerByArtist("gilded",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_GILDED_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_TUNDRA_MUSIC_DISC = registerByArtist("tundra",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_TUNDRA_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_SHULKER_MUSIC_DISC = registerByArtist("shulker",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_SHULKER_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_PUMPKIN_MUSIC_DISC = registerByArtist("pumpkin",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_PUMPKIN_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_GUARDIAN_MUSIC_DISC = registerByArtist("guardian",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_GUARDIAN_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_5_10KSPECIAL_MUSIC_DISC = registerByArtist("5_10kspecial",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_5_10KSPECIAL_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_THEENDERDRAGON_MUSIC_DISC = registerByArtist("theenderdragon",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_THEENDERDRAGON_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_RAIDERS_MUSIC_DISC = registerByArtist("raiders",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_RAIDERS_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_THEWITHER_MUSIC_DISC = registerByArtist("thewither",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_THEWITHER_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_THUNDERSTORM_MUSIC_DISC = registerByArtist("thunderstorm",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_THUNDERSTORM_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);

        NEM_TENM_1KSPECIAL_MUSIC_DISC = registerByArtist("1kspecial",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_TENM_1KSPECIAL_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.T_EN_M);


        // Laudividni discs

        NEM_LAUDIVIDNI_NIGHTFALL_MUSIC_DISC = registerByArtist("nightfall",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_LAUDIVIDNI_NIGHTFALL_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.LAUDIVIDNI);

        // Stereonyx discs

        NEM_STEREONYX_DRAGONFIGHT_MUSIC_DISC = registerByArtist("dragonfight",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.NEM_STEREONYX_DRAGONFIGHT_KEY)
                        .maxCount(1)
                        .rarity(Rarity.RARE)),
                Artists.STEREONYX);
        */
    }
}
