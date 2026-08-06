package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

import java.util.List;

public class BiomesConditionData implements IConditionData {
    List<String> Biomes;


    public BiomesConditionData(List<String> biomes) {
        Biomes = biomes;
    }


    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this, ctx);
    }
}
