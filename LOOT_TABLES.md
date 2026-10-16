# Loot-table definitions

Not Enough Music can add discs to existing vanilla loot tables. Loot definitions
are stored in the `loots` list of
[definitions.json](src/main/resources/data/notenoughmusic/definitions.json).
A disc can have multiple loot rules.

The custom format is intentionally smaller than Minecraft's complete loot
condition system. It focuses on conditions that are useful for this mod and
easy to read and maintain.

> [!IMPORTANT]
> The mod adds pools to existing vanilla loot tables. It does not replace or
> edit the pools already present in those tables.

## Loot rule structure

```json
{
  "target": "entities/creeper",
  "rolls": 1,
  "conditions": [
    {
      "type": "killed_by_player"
    }
  ],
  "count": {
    "min": 1,
    "max": 1
  }
}
```

| Field       | Type       | Description |
|-------------|------------|-------------|
| `target`    | String     | Vanilla loot-table path, without the `minecraft:` namespace. |
| `rolls`     | Integer    | Number of rolls for the added pool. |
| `conditions`| Array      | Conditions that must all pass. |
| `count`     | Object     | Minimum and maximum item count. |

## Conditions

### Random chance

Type: `random_chance`

| Field    | Type  |
|----------|-------|
| `chance` | Float |

```json
{
  "type": "random_chance",
  "chance": 0.25
}
```

The chance is fixed and is not affected by enchantments.

### Random chance with enchantments

Type: `random_chance_enchantments`

| Field      | Type   |
|------------|--------|
| `enchant`  | String |
| `base`     | Float  |
| `perLevel` | Float  |

```json
{
  "type": "random_chance_enchantments",
  "enchant": "minecraft:sharpness",
  "base": 0.2,
  "perLevel": 0.2
}
```

The chance starts at `base` and increases by `perLevel` for each level of the
specified enchantment.

### Killed by player

Type: `killed_by_player`

```json
{
  "type": "killed_by_player"
}
```

The target entity must be killed by a player.

### Killed by entity

Type: `killed_by`

| Field    | Type   |
|----------|--------|
| `killer` | String |
| `direct` | Boolean (optional) |

```json
{
  "type": "killed_by",
  "killer": "minecraft:creeper",
  "direct": false
}
```

The target must be killed by the specified entity type.

### Killed using an item

Type: `killed_with_item`

| Field  | Type   |
|--------|--------|
| `item` | String |

```json
{
  "type": "killed_with_item",
  "item": "minecraft:diamond_sword"
}
```

The killing item is checked in the mainhand slot.

### Killed using an item tag

Type: `killed_with_item_tag`

| Field | Type   |
|-------|--------|
| `tag` | String |

```json
{
  "type": "killed_with_item_tag",
  "tag": "minecraft:swords"
}
```

The killing item must belong to the specified item tag.

### Biome

Type: `biomes`

| Field    | Type       |
|----------|------------|
| `biomes` | Array of strings |

```json
{
  "type": "biomes",
  "biomes": [
    "minecraft:badlands"
  ]
}
```

The loot context must be in one of the listed biomes.

### Can see the sky

Type: `sky`

| Field    | Type    |
|----------|---------|
| `canSee` | Boolean |

```json
{
  "type": "sky",
  "canSee": true
}
```

### Light

Type: `light`

| Field     | Type    |
|-----------|---------|
| `min`     | Integer |
| `max`     | Integer |
| `exactly` | Integer |

Use `min` for a minimum light level, `max` for a maximum, both for a range,
or `exactly` for one exact level.

```json
{
  "type": "light",
  "exactly": 5
}
```

### Survive explosion

Type: `survive_explosion`

```json
{
  "type": "survive_explosion"
}
```

The item drops only when the block's drop survives an explosion.

## Planned conditions

The following ideas are documented for future work but are not currently
implemented:

- `weather`
- `entity_equipment`
- Additional location fields such as dimensions, structures, height, blocks,
  and fluids.
