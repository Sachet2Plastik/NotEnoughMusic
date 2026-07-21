package sachet2plastik.moremusicdiscs.sound;


import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import sachet2plastik.moremusicdiscs.MoreMusicDiscs;


public class ModSound {
    public static final SoundEvent MMD_TEST;
    public static final RegistryKey<JukeboxSong> MMD_TEST_KEY;

    // T_en_M discs
    public static final SoundEvent MMD_TENM_UNDEAD;
    public static final RegistryKey<JukeboxSong> MMD_TENM_UNDEAD_KEY;


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(MoreMusicDiscs.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        MoreMusicDiscs.LOGGER.info("Registering " + MoreMusicDiscs.MOD_ID + " Musics");
    }

    static {
        MMD_TEST = registerSoundEvent("mmd_test");
        MMD_TEST_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(MoreMusicDiscs.MOD_ID, "mmd_test"));

        // T_en_M discs
        MMD_TENM_UNDEAD = registerSoundEvent("mmd_tenm_undead");
        MMD_TENM_UNDEAD_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(MoreMusicDiscs.MOD_ID, "t_en_m/undead"));
    }
}
