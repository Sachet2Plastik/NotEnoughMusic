package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class KilledByPlayerConditionData implements IConditionData {

    public KilledByPlayerConditionData() {
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter) {
        return dataConverter.buildFromData(this);
    }

}
