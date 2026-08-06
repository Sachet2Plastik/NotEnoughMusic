package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class KilledByConditionData implements IConditionData {
    String Killer;
    Boolean Direct;

    public KilledByConditionData(String killer, Boolean direct) {
        Killer = killer;
        Direct = direct;
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this);
    }
}
