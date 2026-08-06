package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

public interface IConditionData {
    class ConditionContext {
        RegistryKey<LootTable> Key;
        LootTable.Builder TableBuilder;
        LootTableSource Source;
        RegistryWrapper.WrapperLookup Registries;

        public ConditionContext(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) {
            Key = key;
            TableBuilder = tableBuilder;
            Source = source;
            Registries = registries;
        }
    }
    LootCondition.Builder convert(ConditionDataConverter dataConverter, ConditionContext ctx);
}

