package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class KilledWithItemTagConditionData implements IConditionData {
    String Tag;

    public KilledWithItemTagConditionData(String tag) {
        Tag = tag;
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this);
    }

}
