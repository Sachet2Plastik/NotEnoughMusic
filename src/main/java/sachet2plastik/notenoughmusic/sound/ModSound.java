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
        }));
    }

    static {
        NEM_TEST = registerSoundEvent("nem_test");
        NEM_TEST_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(NotEnoughMusic.MOD_ID, "nem_test"));
    }
}
