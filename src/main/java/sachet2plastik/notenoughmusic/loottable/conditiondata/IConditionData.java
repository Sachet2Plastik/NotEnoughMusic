package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public interface IConditionData {
    LootCondition.Builder convert(ConditionDataConverter dataConverter);
}

