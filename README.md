## Eidolon World
Legendary Fantasy World Generation Minecraft

```
EidolonWorld/
│
├── build.gradle
├── gradle.properties
├── settings.gradle
│
├── common/                                  # Code chung cho 3 loader
│   ├── src/main/java/com/eidolonworld/
│   │   ├── EidolonWorld.java                # Entry point (common init)
│   │   ├── worldgen/
│   │   │   ├── EWBiomes.java                # Đăng ký biome
│   │   │   ├── EWNoiseRouter.java           # Noise logic custom
│   │   │   ├── EWSurfaceRules.java          # Surface layers
│   │   │   ├── EWTiers.java                 # Enum tier biome
│   │   │   └── mixin/
│   │   │        ├── NoiseChunkGeneratorMixin.java
│   │   │        ├── BiomeSourceMixin.java
│   │   │        └── SurfaceRulesMixin.java
│   │   └── util/
│   │        └── NoiseUtils.java
│   │
│   └── src/main/resources/
│       ├── data/eidolonworld/
│       │   ├── worldgen/
│       │   │   ├── biome/
│       │   │   │   ├── crystal_ridge.json
│       │   │   │   ├── aurora_grove.json
│       │   │   │   └── void_plains.json
│       │   │   ├── noise_settings/
│       │   │   │   ├── eidolon_overworld.json
│       │   │   │   └── mythic_layers.json
│       │   │   ├── structure/
│       │   │   │   ├── aurora_tower.json
│       │   │   │   └── forgotten_ruins.json
│       │   │   └── template_pool/
│       │   │        └── eidolon_tower_start.json
│       │   └── tags/worldgen/biome/
│       │        ├── is_legendary.json
│       │        └── is_common.json
│       └── mixins.eidolonworld.json
│
├── fabric/
│   └── src/main/resources/
│       └── fabric.mod.json
│
├── forge/
│   └── src/main/resources/
│       └── META-INF/mods.toml
│
├── neoforge/
│   └── src/main/resources/
│       └── META-INF/mods.toml
│
└── README.md
```
