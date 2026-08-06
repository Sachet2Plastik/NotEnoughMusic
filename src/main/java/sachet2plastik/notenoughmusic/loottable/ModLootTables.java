package sachet2plastik.notenoughmusic.loottable;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import sachet2plastik.notenoughmusic.DiscsRegistry;
import sachet2plastik.notenoughmusic.loottable.conditiondata.ConditionDataConverter;
import sachet2plastik.notenoughmusic.loottable.conditiondata.IConditionData;

public class ModLootTables {
    public static void registerLootTables() {

        DiscsRegistry.DISCS.forEach((name, definition) -> {

            definition.Loots.forEach((lootDefinition -> {
                Identifier targetID = Identifier.of("minecraft", lootDefinition.Target);

                LootTableEvents.MODIFY.register(((key, tableBuilder, source, registries) -> {
                    if (source.isBuiltin() && targetID.equals(key.getValue())) {
                        LootPool.Builder poolBuilder = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(lootDefinition.Rolls))
                                .with(ItemEntry.builder(definition.Item))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(lootDefinition.CountMin, lootDefinition.CountMax)));

                                lootDefinition.Conditions.forEach((condition -> {
                                    poolBuilder.conditionally(condition.convert(new ConditionDataConverter(), new IConditionData.ConditionContext(key, tableBuilder, source, registries)));
                                }));
                                poolBuilder.build();

                        tableBuilder.pool(poolBuilder.build());
                    }

                }));

            }));

        });
    }
}
