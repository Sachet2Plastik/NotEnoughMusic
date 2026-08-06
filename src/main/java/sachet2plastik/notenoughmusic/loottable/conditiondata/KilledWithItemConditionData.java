package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;

public class KilledWithItemConditionData implements IConditionData {
    String Item;

    public KilledWithItemConditionData(String item) {
        Item = item;
    }

    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this);
    }

}
