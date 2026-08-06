package sachet2plastik.notenoughmusic.loottable.conditiondata;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;


public class ConditionDataConverter {

    public LootCondition.Builder buildFromData(RandomChanceConditionData data) {
        return RandomChanceLootCondition.builder(data.Chance);
    }

    public LootCondition.Builder buildFromData(RandomChancesEnchantmentConditionData data, IConditionData.ConditionContext ctx) {
        Identifier enchantID = Identifier.of(data.Enchant);
        RegistryKey<Enchantment> enchantKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, enchantID);

        RegistryEntry<Enchantment> enchantmentEntry = ctx.Registries
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(enchantKey);

        return () -> new RandomChanceWithEnchantedBonusLootCondition(
                data.Base,
                new EnchantmentLevelBasedValue.Linear(data.PerLevel, 0.0f),
                enchantmentEntry
        );
    }

    @SuppressWarnings("unused")
    public LootCondition.Builder buildFromData(KilledByPlayerConditionData unused) {
        return KilledByPlayerLootCondition.builder();
    }

    public LootCondition.Builder buildFromData(KilledByConditionData data) {
        Identifier killerID = Identifier.of(data.Killer);
        EntityType<?> killerType = Registries.ENTITY_TYPE.get(killerID);
        return EntityPropertiesLootCondition.builder(
                data.Direct ? LootContext.EntityTarget.DIRECT_ATTACKER : LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.create().type(EntityTypePredicate.create(killerType))
        );
    }

    public LootCondition.Builder buildFromData(KilledWithItemConditionData data) {
        Identifier itemID = Identifier.of(data.Item);
        return EntityPropertiesLootCondition.builder(
                LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.create().equipment(
                        EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().items(Registries.ITEM.get(itemID))).build()
                ).build()
        );
    }

    public LootCondition.Builder buildFromData(KilledWithItemTagConditionData data) {
        Identifier tagID = Identifier.of(data.Tag);
        TagKey<Item> tagKey = TagKey.of(RegistryKeys.ITEM, tagID);
        return EntityPropertiesLootCondition.builder(
                LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.create().equipment(
                        EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().tag(tagKey))
                )
        );
    }

    public LootCondition.Builder buildFromData(BiomesConditionData data, IConditionData.ConditionContext ctx) {
        ArrayList<RegistryEntry<Biome>> biomeEntries = new ArrayList<>(List.of());
        for (String biome : data.Biomes) {
            Identifier biomeID = Identifier.of(biome);
            RegistryKey<Biome> biomeKey = RegistryKey.of(RegistryKeys.BIOME, biomeID);

            RegistryEntry<Biome> biomeEntry = ctx.Registries
                    .getWrapperOrThrow(RegistryKeys.BIOME)
                    .getOrThrow(biomeKey);
            biomeEntries.addLast(biomeEntry);
        }
        RegistryEntryList<Biome> biomesEntryList = RegistryEntryList.of(biomeEntries);
        return LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(biomesEntryList));
    }

    public LootCondition.Builder buildFromData(SkyConditionData data) {
        return LocationCheckLootCondition.builder(LocationPredicate.Builder.create().canSeeSky(data.CanSeeTheSky));
    }

    public LootCondition.Builder buildFromData(LightConditionData data) {
        if (data.Exactly >= 0) {
            return LocationCheckLootCondition.builder(
                    LocationPredicate.Builder.create().light(
                            LightPredicate.Builder.create().light(NumberRange.IntRange.exactly(data.Exactly))
                    ));
        }

        if (data.Min < 0 && data.Max >= 0) {
            return LocationCheckLootCondition.builder(
                    LocationPredicate.Builder.create().light(
                            LightPredicate.Builder.create().light(NumberRange.IntRange.atMost(data.Max))
                    ));
        }

        if (data.Min >= 0 && data.Max < 0) {
            return LocationCheckLootCondition.builder(
                    LocationPredicate.Builder.create().light(
                            LightPredicate.Builder.create().light(NumberRange.IntRange.atLeast(data.Max))
                    ));
        }

        return LocationCheckLootCondition.builder(
                LocationPredicate.Builder.create().light(
                        LightPredicate.Builder.create().light(NumberRange.IntRange.between(data.Min, data.Max))
                ));

    }

    @SuppressWarnings("unused")
    public LootCondition.Builder buildFromData(SurviveExplosionConditionData unused) {
        return SurvivesExplosionLootCondition.builder();
    }




}