# Not Enough Music

**Not Enough Music** (NEM) is a Fabric mod that adds a collection of Minecraft fan-made music found on YouTube.
The goal of this project is to gather these tracks into a single mod while giving proper credit to every artist.
More than just adding the tracks themselve, this mod also adds more different way to obtain them, diversifying the collecting process.

As a Minecraft disc collector myself, I felt like collecting only the vanilla ones started to get repetitive,
which is why I made this mod in the first place.

Have fun and go collect the maximum of discs !

## Credits

This mod includes music from the following creators:

* [T_en_M](https://www.youtube.com/@TenM)
* [Laudividni](https://www.youtube.com/@LaudividniMusic)
* [Stereonyx](https://www.youtube.com/@Stereonyx)

More artists may be added in future updates.

## License

The **source code** of this project is licensed under the **MIT License**.

> [!IMPORTANT]
> The MIT License applies <u>**ONLY to the source code</u>** in this repository. It does **not** apply to the music assets.

> [!NOTE]
> The music included in this mod remains the property of its respective creators.
>
> Each track is used in accordance with the usage permissions provided by its original creator. Please refer to the original source or creator's page for the specific terms under which it may be used.

> [!WARNING]
> Do **not** assume that the music assets are free to redistribute, modify, or reuse outside of this project simply because the source code is MIT licensed.

## Contributing

Any contributions are welcome given a proper PR or Issue detailing the changes.

To work on the project, download the repository and set up your environment by following the Fabric documentation.

## Adding a disc

Adding a disc require a few steps but pretty straight forward :

1. Prepare the item texture and the song file (`.ogg`).
2. Place them in their respective folders:
   - [Disc textures](src/main/resources/assets/notenoughmusic/textures/item/discs)
   - [Music files](src/main/resources/assets/notenoughmusic/sounds/musics)
3. Add an entry to [definitions.json](src/main/resources/data/notenoughmusic/definitions.json). The file name must match the definition's `name` entry.
4. If adding a new artist, add the artist to [DiscsRegistry.java](src/main/java/sachet2plastik/notenoughmusic/DiscsRegistry.java), update [AutoGen.py](AutoGen.py), and create the corresponding directories.
5. Run [AutoGen.py](AutoGen.py) to generate the required resource files.

For the loot-definition format, see [LOOT_TABLES.md](LOOT_TABLES.md).
