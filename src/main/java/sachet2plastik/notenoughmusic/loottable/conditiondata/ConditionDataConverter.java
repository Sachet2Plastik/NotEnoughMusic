package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;


public class ConditionDataConverter {

    public LootCondition.Builder buildFromData(RandomChanceConditionData data) {
        return RandomChanceLootCondition.builder(data.Chance);
    }

    public LootCondition.Builder buildFromData(KilledByPlayerConditionData data) {

        return KilledByPlayerLootCondition.builder();
    }




}