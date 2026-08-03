package sachet2plastik.notenoughmusic;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

import sachet2plastik.notenoughmusic.DiscsRegistry.Artists;
import sachet2plastik.notenoughmusic.loottable.conditiondata.IConditionData;
import sachet2plastik.notenoughmusic.loottable.LootDefinition;
import sachet2plastik.notenoughmusic.loottable.conditiondata.KilledByPlayerConditionData;
import sachet2plastik.notenoughmusic.loottable.conditiondata.RandomChanceConditionData;

import java.util.ArrayList;
import java.util.List;

import static sachet2plastik.notenoughmusic.DiscsRegistry.resolveRarity;

public class DiscDefinition {
    DiscDefinition(
            String name,
            Artists artist,
            SoundEvent soundEvent,
            RegistryKey<JukeboxSong> key,
            Item item,
            Integer maxCount,
            Rarity rarity,
            String description,
            Integer comparatorOutput,
            Float lengthInSeconds,
            List<LootDefinition> loots
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
        Loots = loots;
    }

    public record RawCondition(
            String type,
            Float chance
    ) {}

    public record RawCount(
            Integer min,
            Integer max
    ) {}

    public record RawLoot(
            String target,
            List<RawCondition> conditions,
            RawCount count,
            Integer rolls
    ) {}

    public record RawDiscDefinition(
            String name,
            String artist,
            Integer maxCount,
            String rarity,
            String description,
            Integer comparatorOutput,
            Float lengthInSeconds,
            List<RawLoot> loots
    ) {}

    public static DiscDefinition convertRaw(RawDiscDefinition raw) {
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
                raw.lengthInSeconds(),
                convertRawLoots(raw.loots())
        );
    }

    private static IConditionData buildConditionData(RawCondition data) {
        switch (data.type()) {
            case "random_chance":
                return new RandomChanceConditionData(data.chance());
            case "killed_by_player":
                return new KilledByPlayerConditionData();
        }
        return null;
    }

    private static String DebugBuildString(String buff, RawCondition data) {
        switch (data.type()) {
            case "random_chance":
                return buff.concat("{ Type: " + data.type() + ", Chance: " + data.chance() + " },\n");
            case "killed_by_player":
                return buff.concat("{ Type: " + data.type() + " },\n");
                default:
                return buff.concat("{condition not implemented yet}\n");
        }
    }

    private static List<LootDefinition> convertRawLoots(List<RawLoot> raw) {
        ArrayList<LootDefinition> lootDefinitions = new ArrayList<>(List.of());

        if (raw == null)
            return lootDefinitions;

        for (RawLoot loot : raw) {
            LootDefinition toAdd = new LootDefinition();
            toAdd.Target = loot.target;
            String buff = "";
            buff = buff.concat("Target: " + toAdd.Target + "\nConditions: [\n");
            toAdd.Conditions = new ArrayList<>(List.of());
            for (RawCondition condition : loot.conditions()) {
                toAdd.Conditions.addLast(buildConditionData(condition));
                buff = DebugBuildString(buff, condition);
            }
            toAdd.CountMin = loot.count().min();
            toAdd.CountMax = loot.count().max();
            toAdd.Rolls = loot.rolls;
            buff = buff.concat("]\nCount: [" + toAdd.CountMin + " ; " + toAdd.CountMax + "], Rolls: " + toAdd.Rolls + "\n");
            System.out.println(buff);
            lootDefinitions.addLast(toAdd);
        }
        return lootDefinitions;
    }

    public String Name;
    public Artists Artist;

    public Integer MaxCount;
    public Rarity Rarity;

    public String Description;
    public Integer ComparatorOutput;
    public Float LengthInSeconds;

    public Item Item;
    public SoundEvent SoundEvent;
    public RegistryKey<JukeboxSong> Key;

    public List<LootDefinition> Loots;
}
