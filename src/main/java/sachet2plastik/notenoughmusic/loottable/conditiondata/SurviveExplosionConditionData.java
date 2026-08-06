package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class SurviveExplosionConditionData implements IConditionData {
    public SurviveExplosionConditionData() {
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this);
    }
}
