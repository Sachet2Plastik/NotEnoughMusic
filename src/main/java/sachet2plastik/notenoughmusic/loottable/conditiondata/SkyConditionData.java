package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.LootCondition;


public class SkyConditionData implements IConditionData {
    Boolean CanSeeTheSky;

    public SkyConditionData(Boolean canSeeTheSky) {
        CanSeeTheSky = canSeeTheSky;
    }


    @Override
    public LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx) {
        return dataConverter.buildFromData(this);
    }
}
