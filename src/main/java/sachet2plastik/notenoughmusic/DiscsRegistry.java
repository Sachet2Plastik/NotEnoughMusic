package sachet2plastik.notenoughmusic;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscsRegistry {
    public enum Artists {
        T_EN_M("t_en_m/", "tenm"),
        LAUDIVIDNI("laudividni/", "laudividni"),
        STEREONYX("stereonyx/", "stereonyx");

        private final String Prefix;
        private final String FormatedName;

        Artists(String prefix, String name) {
            Prefix = prefix;
            FormatedName = name;
        }

        public String prefix() {
            return Prefix;
        }

        public String Name() {
            return FormatedName;
        }

        public static Artists resolveArtist(String name) {
            for (Artists artist : Artists.values()) {
                if (artist.FormatedName.equals(name)) {
                    return artist;
                }
            }
            return null;
        }
    }

    public static Rarity resolveRarity(String name) {
        for (Rarity rarity : Rarity.values()) {
            if (rarity.name().equals(name)) {
                return rarity;
            }
        }
        return null;
    }

    public record RawDiscDefinition(
            String name,
            String artist,
            Integer maxCount,
            String rarity,
            String description,
            Integer comparatorOutput,
            Float lengthInSeconds
    ) {}

    private static DiscDefinition convertRaw(RawDiscDefinition raw) {
        return new DiscDefinition(
                raw.name(),
                Artists.resolveArtist(raw.artist()),
                null,
                null,
                null,
                raw.maxCount(),
                resolveRarity(raw.rarity()),
                raw.description(),
                raw.comparatorOutput(),
                raw.lengthInSeconds()
        );
    }

    public static final Map<String, DiscDefinition> DISCS = new HashMap<>();
    private static final Type DEFINITION_TYPE = new TypeToken<List<RawDiscDefinition>>() {}.getType();

    public static void printResource(String path) {
        InputStream in = NotEnoughMusic.class
                .getResourceAsStream(path);

        if (in == null) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            NotEnoughMusic.LOGGER.error("Error during resource printing");
        }
    }



    public static void registerDiscFromConfig() {
        InputStream stream = NotEnoughMusic.class.getResourceAsStream("/data/notenoughmusic/definitions.json");

        if (stream == null) {
            NotEnoughMusic.LOGGER.error("Error: could not read discs definitions json file;\n" +
                    "Make sure the 'definitions.json' file is in 'ressources/data/notenoughmusic/' folder");
            return;
        }
        Gson gson = new Gson();

        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        List<RawDiscDefinition> PARSED_DISCS = gson.fromJson(reader, DEFINITION_TYPE);

        for (RawDiscDefinition dd : PARSED_DISCS) {
            DISCS.put(dd.name(), convertRaw(dd));

            /*
            System.out.println(
                    "Name: " +  dd.name() + "\n" +
                    "Artist: " + dd.artist() + "\n"+
                    "Max Count: " + dd.maxCount() + "\n" +
                    "Rariry: " + dd.rarity() + "\n" +
                    "Destription: " + dd.description() + "\n" +
                    "Comparator Output: " + dd.comparatorOutput() + "\n" +
                    "Lenght: " + dd.lengthInSeconds() + "\n----------\n");*/
        }


    }
}
