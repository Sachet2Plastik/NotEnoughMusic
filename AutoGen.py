from enum import Enum
import json

ARTISTS = {
    "tenm" : {"name": "tenm", "prefix": "t_en_m/", "raw": "t_en_m"},
    "laudividni" : {"name": "laudividni", "prefix": "laudividni/", "raw": "laudividni"},
    "stereonyx" : {"name": "stereonyx", "prefix": "stereonyx/", "raw": "stereonyx"}
}

MOD_ID = "notenoughmusic"
NEM = "nem_"

RESOURCES_DIR = "src/main/resources/"
DATA_DIR = RESOURCES_DIR + "data/notenoughmusic/"
JUKEBOX_DIR = DATA_DIR + "jukebox_song/"

ASSETS_DIR = RESOURCES_DIR + "assets/notenoughmusic/"
LANG_DIR = ASSETS_DIR + "lang/"
MODELS_ITEM_DISCS_DIR = ASSETS_DIR + "models/item/discs/"
SOUNDS_MUSICS_DIR = ASSETS_DIR + "sounds/musics/"
TEXTURES_ITEM_DISCS_DIR = ASSETS_DIR + "textures/item/discs/"

JUKEBOX_SONG = {
    "comparator_output": 15,
    "description": {
        "translate": "Artist - Title"
    },
    "length_in_seconds": 120.0,
    "sound_event": "notenoughmusic:nem_tenm_voyage"
}

# directly generate the file
def GenJukeboxSong(data):
    jukebox_song = dict(JUKEBOX_SONG)

    jukebox_song["comparator_output"] = data["comparatorOutput"]
    jukebox_song["description"]["translate"] = data["description"]
    jukebox_song["length_in_seconds"] = data["lengthInSeconds"]
    jukebox_song["sound_event"] = MOD_ID + ":" + NEM + ARTISTS[data["artist"]]["name"] + "_" + data["name"]

    print(json.dumps(jukebox_song, indent=4))



SOUNDS = {
    "nem_test": {
        "sounds": [
            {
                "name": "notenoughmusic:musics/test/multimilionaire",
                "stream": True
            }
        ]
    },
}

# DO NOT directly generate the file
def GenMainSounds(data):
    SOUNDS[NEM + data["name"]] = {
        "sounds": [
            {
                "name": MOD_ID + ":musics/" + ARTISTS[data["artist"]]["prefix"] + data["name"],
                "stream": True
            }
        ]
    }

    print(json.dumps(SOUNDS, indent=4))


LANG = {
    "item.notenoughmusic.discs.test.nem_test_music_disc": "Mod Maker",
}

# DO NOT directly generate the file
def GenLang(data, lang: str = "en_us.json"):
    # lang parameter if used as target file
    LANG["item." + MOD_ID + ".discs." + ARTISTS[data["artist"]]["raw"] + "." + NEM + data["name"] + "_music_disc"] = data["itemName"]

    print(json.dumps(LANG, indent=4))


MODELS = {
    "parent": "minecraft:item/generated",
    "textures": {
        "layer0": "notenoughmusic:item/discs/test/nem_test"
    }
}

# directly generate the file
def GenModel(data):
    model = dict(MODELS)

    model["textures"]["layer0"] = MOD_ID + ":item/discs/" + ARTISTS[data["artist"]]["prefix"] + data["name"]

    print(json.dumps(model, indent=4))



with open("src/main/resources/data/notenoughmusic/definitions.json", "r", encoding="utf-8") as f:
    data = json.load(f)

    for desc in data:
        GenModel(desc)
        break