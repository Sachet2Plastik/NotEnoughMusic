package sachet2plastik.notenoughmusic.sound;


import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import sachet2plastik.notenoughmusic.DiscsRegistry;
import sachet2plastik.notenoughmusic.NotEnoughMusic;
import sachet2plastik.notenoughmusic.DiscsRegistry.Artists;

import java.util.Map;


public class ModSound {
    public static final SoundEvent NEM_TEST;
    public static final RegistryKey<JukeboxSong> NEM_TEST_KEY;

    // T_en_M discs
    /*
    public static final SoundEvent NEM_TENM_UNDEAD;
    public static final RegistryKey<JukeboxSong> NEM_TENM_UNDEAD_KEY;
    public static final SoundEvent NEM_TENM_FROSTED;
    public static final RegistryKey<JukeboxSong> NEM_TENM_FROSTED_KEY;
    public static final SoundEvent NEM_TENM_SOULLESS;
    public static final RegistryKey<JukeboxSong> NEM_TENM_SOULLESS_KEY;
    public static final SoundEvent NEM_TENM_VOYAGE;
    public static final RegistryKey<JukeboxSong> NEM_TENM_VOYAGE_KEY;
    public static final SoundEvent NEM_TENM_BREEZE;
    public static final RegistryKey<JukeboxSong> NEM_TENM_BREEZE_KEY;
    public static final SoundEvent NEM_TENM_MINESHAFT;
    public static final RegistryKey<JukeboxSong> NEM_TENM_MINESHAFT_KEY;
    public static final SoundEvent NEM_TENM_BELOW;
    public static final RegistryKey<JukeboxSong> NEM_TENM_BELOW_KEY;
    public static final SoundEvent NEM_TENM_NOESCAPE;
    public static final RegistryKey<JukeboxSong> NEM_TENM_NOESCAPE_KEY;
    public static final SoundEvent NEM_TENM_GILDED;
    public static final RegistryKey<JukeboxSong> NEM_TENM_GILDED_KEY;
    public static final SoundEvent NEM_TENM_TUNDRA;
    public static final RegistryKey<JukeboxSong> NEM_TENM_TUNDRA_KEY;
    public static final SoundEvent NEM_TENM_SHULKER;
    public static final RegistryKey<JukeboxSong> NEM_TENM_SHULKER_KEY;
    public static final SoundEvent NEM_TENM_PUMPKIN;
    public static final RegistryKey<JukeboxSong> NEM_TENM_PUMPKIN_KEY;
    public static final SoundEvent NEM_TENM_GUARDIAN;
    public static final RegistryKey<JukeboxSong> NEM_TENM_GUARDIAN_KEY;
    public static final SoundEvent NEM_TENM_5_10KSPECIAL;
    public static final RegistryKey<JukeboxSong> NEM_TENM_5_10KSPECIAL_KEY;
    public static final SoundEvent NEM_TENM_THEENDERDRAGON;
    public static final RegistryKey<JukeboxSong> NEM_TENM_THEENDERDRAGON_KEY;
    public static final SoundEvent NEM_TENM_RAIDERS;
    public static final RegistryKey<JukeboxSong> NEM_TENM_RAIDERS_KEY;
    public static final SoundEvent NEM_TENM_THEWITHER;
    public static final RegistryKey<JukeboxSong> NEM_TENM_THEWITHER_KEY;
    public static final SoundEvent NEM_TENM_THUNDERSTORM;
    public static final RegistryKey<JukeboxSong> NEM_TENM_THUNDERSTORM_KEY;
    public static final SoundEvent NEM_TENM_1KSPECIAL;
    public static final RegistryKey<JukeboxSong> NEM_TENM_1KSPECIAL_KEY;
    // Laudividni discs
    public static final SoundEvent NEM_LAUDIVIDNI_NIGHTFALL;
    public static final RegistryKey<JukeboxSong> NEM_LAUDIVIDNI_NIGHTFALL_KEY;

    // Steronyx discs
    public static final SoundEvent NEM_STEREONYX_DRAGONFIGHT;
    public static final RegistryKey<JukeboxSong> NEM_STEREONYX_DRAGONFIGHT_KEY;
*/
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, name);

        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    private static SoundEvent registerMusic(String name, Artists artist) {
        String formatedName = NotEnoughMusic.MOD_FORMAT_NAME + "_" + artist.Name() + "_" + name;
        return registerSoundEvent(formatedName);
    }

    private static RegistryKey<JukeboxSong> registerKeyByArtist(String name, Artists artist) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, artist.prefix() + name);

        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, id);
    }

    public static void registerSounds() {
        NotEnoughMusic.LOGGER.info("Registering " + NotEnoughMusic.MOD_ID + " Musics");

        DiscsRegistry.DISCS.forEach(((name, definition) -> {
            definition.SoundEvent = registerMusic(definition.Name, definition.Artist);
            definition.Key = registerKeyByArtist(definition.Name, definition.Artist);

            DiscsRegistry.DISCS.put(name, definition);
            NotEnoughMusic.LOGGER.info("{} -> {}", definition.Name, definition.Key);
        }));
    }

    static {
        NEM_TEST = registerSoundEvent("nem_test");
        NEM_TEST_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(NotEnoughMusic.MOD_ID, "nem_test"));

        // T_en_M discs
        /*
        NEM_TENM_UNDEAD = registerMusic("undead", Artists.T_EN_M);
        NEM_TENM_UNDEAD_KEY = registerKeyByArtist("undead", Artists.T_EN_M);

        NEM_TENM_FROSTED = registerMusic("frosted", Artists.T_EN_M);
        NEM_TENM_FROSTED_KEY = registerKeyByArtist("frosted", Artists.T_EN_M);

        NEM_TENM_SOULLESS = registerMusic("soulless", Artists.T_EN_M);
        NEM_TENM_SOULLESS_KEY = registerKeyByArtist("soulless", Artists.T_EN_M);

        NEM_TENM_VOYAGE = registerMusic("voyage", Artists.T_EN_M);
        NEM_TENM_VOYAGE_KEY = registerKeyByArtist("voyage", Artists.T_EN_M);

        NEM_TENM_BREEZE = registerMusic("breeze", Artists.T_EN_M);
        NEM_TENM_BREEZE_KEY = registerKeyByArtist("breeze", Artists.T_EN_M);

        NEM_TENM_MINESHAFT = registerMusic("mineshaft", Artists.T_EN_M);
        NEM_TENM_MINESHAFT_KEY = registerKeyByArtist("mineshaft", Artists.T_EN_M);

        NEM_TENM_BELOW = registerMusic("below", Artists.T_EN_M);
        NEM_TENM_BELOW_KEY = registerKeyByArtist("below", Artists.T_EN_M);

        NEM_TENM_NOESCAPE = registerMusic("noescape", Artists.T_EN_M);
        NEM_TENM_NOESCAPE_KEY = registerKeyByArtist("noescape", Artists.T_EN_M);

        NEM_TENM_GILDED = registerMusic("gilded", Artists.T_EN_M);
        NEM_TENM_GILDED_KEY = registerKeyByArtist("gilded", Artists.T_EN_M);

        NEM_TENM_TUNDRA = registerMusic("tundra", Artists.T_EN_M);
        NEM_TENM_TUNDRA_KEY = registerKeyByArtist("tundra", Artists.T_EN_M);

        NEM_TENM_SHULKER = registerMusic("shulker", Artists.T_EN_M);
        NEM_TENM_SHULKER_KEY = registerKeyByArtist("shulker", Artists.T_EN_M);

        NEM_TENM_PUMPKIN = registerMusic("pumpkin", Artists.T_EN_M);
        NEM_TENM_PUMPKIN_KEY = registerKeyByArtist("pumpkin", Artists.T_EN_M);

        NEM_TENM_GUARDIAN = registerMusic("guardian", Artists.T_EN_M);
        NEM_TENM_GUARDIAN_KEY = registerKeyByArtist("guardian", Artists.T_EN_M);

        NEM_TENM_5_10KSPECIAL = registerMusic("5_10kspecial", Artists.T_EN_M);
        NEM_TENM_5_10KSPECIAL_KEY = registerKeyByArtist("5_10kspecial", Artists.T_EN_M);

        NEM_TENM_THEENDERDRAGON = registerMusic("theenderdragon", Artists.T_EN_M);
        NEM_TENM_THEENDERDRAGON_KEY = registerKeyByArtist("theenderdragon", Artists.T_EN_M);

        NEM_TENM_RAIDERS = registerMusic("raiders", Artists.T_EN_M);
        NEM_TENM_RAIDERS_KEY = registerKeyByArtist("raiders", Artists.T_EN_M);

        NEM_TENM_THEWITHER = registerMusic("thewither", Artists.T_EN_M);
        NEM_TENM_THEWITHER_KEY = registerKeyByArtist("thewither", Artists.T_EN_M);

        NEM_TENM_THUNDERSTORM = registerMusic("thunderstorm", Artists.T_EN_M);
        NEM_TENM_THUNDERSTORM_KEY = registerKeyByArtist("thunderstorm", Artists.T_EN_M);

        NEM_TENM_1KSPECIAL = registerMusic("1kspecial", Artists.T_EN_M);
        NEM_TENM_1KSPECIAL_KEY = registerKeyByArtist("1kspecial", Artists.T_EN_M);

        // Laudividni discs
        NEM_LAUDIVIDNI_NIGHTFALL = registerMusic("nightfall", Artists.LAUDIVIDNI);
        NEM_LAUDIVIDNI_NIGHTFALL_KEY = registerKeyByArtist("nightfall", Artists.LAUDIVIDNI);

        // Stereonyx discs
        NEM_STEREONYX_DRAGONFIGHT = registerMusic("dragonfight", Artists.STEREONYX);
        NEM_STEREONYX_DRAGONFIGHT_KEY = registerKeyByArtist("dragonfight", Artists.STEREONYX);
    */
    }
}
