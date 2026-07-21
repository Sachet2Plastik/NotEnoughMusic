package sachet2plastik.moremusicdiscs.item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sachet2plastik.moremusicdiscs.MoreMusicDiscs;
import sachet2plastik.moremusicdiscs.sound.ModSound;

import java.util.EnumMap;


public class ModItems {
    private enum Artists {
        T_EN_M("t_en_m/"),
        LAUDIVIDNI("laudividni/"),
        STEREONYX("stereonyx/");

        private final String Prefix;

        Artists(String prefix) {
            Prefix = prefix;
        }

        public String prefix() {
            return Prefix;
        }
    }

    public static final Item TEST_MUSIC_DISC;

    // T_en_M discs
    public static final Item MMD_UNDEAD_MUSIC_DISC;



    private static Item registerItem(String name, Item item) {
        Identifier id = Identifier.of(MoreMusicDiscs.MOD_ID, "discs/" + name);

        return Registry.register(Registries.ITEM, id, item);
    }

    private static Item registerByArtist(String name, Item item, Artists artist) {
        return registerItem(artist.prefix() + name, item);
    }

    public static void registerModItems() {
        MoreMusicDiscs.LOGGER.info("Registering " + MoreMusicDiscs.MOD_ID + " Items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(TEST_MUSIC_DISC);
            entries.add(MMD_UNDEAD_MUSIC_DISC);
        });
    }

    static {
        TEST_MUSIC_DISC = registerItem("test/mmd_test_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.MMD_TEST_KEY)
                                            .maxCount(1)
                                            .rarity(Rarity.COMMON)));

        MMD_UNDEAD_MUSIC_DISC = registerByArtist("mmd_tenm_undead_music_disc",
                new Item(new Item.Settings().jukeboxPlayable(ModSound.MMD_TENM_UNDEAD_KEY)
                        .maxCount(1)
                        .rarity(Rarity.UNCOMMON)),
                Artists.T_EN_M);
    }
}
