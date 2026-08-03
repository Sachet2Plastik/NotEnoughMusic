package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class RandomChanceConditionData implements IConditionData {
    Float Chance;

    public RandomChanceConditionData(Float chance) {
        Chance = chance;
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter) {
        return dataConverter.buildFromData(this);
    }

}
