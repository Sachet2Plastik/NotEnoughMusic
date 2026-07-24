package sachet2plastik.notenoughmusic;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

public class DiscDefinition {
    DiscDefinition(
            String name,
            DiscsRegistry.Artists artist,
            SoundEvent soundEvent,
            RegistryKey<JukeboxSong> key,
            Item item,
            Integer maxCount,
            Rarity rarity,
            String description,
            Integer comparatorOutput,
            Float lengthInSeconds
    ) {
        Name = name;
        Artist = artist;
        SoundEvent = soundEvent;
        Key = key;
        Item = item;
        MaxCount = maxCount;
        Rarity = rarity;
        Description = description;
        ComparatorOutput = comparatorOutput;
        LengthInSeconds = lengthInSeconds;
    }

    public String Name;
    public DiscsRegistry.Artists Artist;
    public SoundEvent SoundEvent;
    public RegistryKey<JukeboxSong> Key;
    public Item Item;
    public Integer MaxCount;
    public Rarity Rarity;
    public String Description;
    public Integer ComparatorOutput;
    public Float LengthInSeconds;
}
