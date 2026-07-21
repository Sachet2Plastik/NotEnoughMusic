package sachet2plastik.notenoughmusic.sound;


import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import sachet2plastik.notenoughmusic.NotEnoughMusic;
import sachet2plastik.notenoughmusic.NotEnoughMusic.Artists;



public class ModSound {
    public static final SoundEvent NEM_TEST;
    public static final RegistryKey<JukeboxSong> NEM_TEST_KEY;

    // T_en_M discs
    public static final SoundEvent NEM_TENM_UNDEAD;
    public static final RegistryKey<JukeboxSong> NEM_TENM_UNDEAD_KEY;
    public static final SoundEvent NEM_TENM_FROSTED;
    public static final RegistryKey<JukeboxSong> NEM_TENM_FROSTED_KEY;
    public static final SoundEvent NEM_TENM_SOULLESS;
    public static final RegistryKey<JukeboxSong> NEM_TENM_SOULLESS_KEY;

    // Laudividni discs
    public static final SoundEvent NEM_LAUDIVIDNI_NIGHTFALL;
    public static final RegistryKey<JukeboxSong> NEM_LAUDIVIDNI_NIGHTFALL_KEY;

    // Steronyx discs
    public static final SoundEvent NEM_STEREONYX_DRAGONFIGHT;
    public static final RegistryKey<JukeboxSong> NEM_STEREONYX_DRAGONFIGHT_KEY;

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, name);

        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    private static RegistryKey<JukeboxSong> registerKeyByArtist(String name, Artists artist) {
        Identifier id = Identifier.of(NotEnoughMusic.MOD_ID, artist.prefix() + name);

        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, id);
    }

    public static void registerSounds() {
        NotEnoughMusic.LOGGER.info("Registering " + NotEnoughMusic.MOD_ID + " Musics");
    }

    static {
        NEM_TEST = registerSoundEvent("nem_test");
        NEM_TEST_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(NotEnoughMusic.MOD_ID, "nem_test"));

        // T_en_M discs
        NEM_TENM_UNDEAD = registerSoundEvent("nem_tenm_undead");
        NEM_TENM_UNDEAD_KEY = registerKeyByArtist("undead", Artists.T_EN_M);

        NEM_TENM_FROSTED = registerSoundEvent("nem_tenm_frosted");
        NEM_TENM_FROSTED_KEY = registerKeyByArtist("frosted", Artists.T_EN_M);

        NEM_TENM_SOULLESS = registerSoundEvent("nem_tenm_soulless");
        NEM_TENM_SOULLESS_KEY = registerKeyByArtist("soulless", Artists.T_EN_M);

        // Laudividni discs
        NEM_LAUDIVIDNI_NIGHTFALL = registerSoundEvent("nem_laudividni_nightfall");
        NEM_LAUDIVIDNI_NIGHTFALL_KEY = registerKeyByArtist("nightfall", Artists.LAUDIVIDNI);

        // Stereonyx discs
        NEM_STEREONYX_DRAGONFIGHT = registerSoundEvent("nem_stereonyx_dragonfight");
        NEM_STEREONYX_DRAGONFIGHT_KEY = registerKeyByArtist("dragonfight", Artists.STEREONYX);
    }
}
