from enum import Enum
import json

ARTISTS = {
    "tenm" : {"name": "tenm", "prefix": "t_en_m/", "raw": "t_en_m"},
    "laudividni" : {"name": "laudividni", "prefix": "laudividni/", "raw": "laudividni"},
    "stereonyx" : {"name": "stereonyx", "prefix": "stereonyx/", "raw": "stereonyx"}
}

MOD_ID = "notenoughmusic"
NEM = "nem_"
ITEM_DISCS_SUFFIX = "_music_disc"
JSON_SUFFIX = ".json"

DEFINITIONS = "src/main/resources/data/notenoughmusic/definitions.json"

RESOURCES_DIR = "src/main/resources/"
DATA_DIR = RESOURCES_DIR + "data/notenoughmusic/"
JUKEBOX_DIR = DATA_DIR + "jukebox_song/"

ASSETS_DIR = RESOURCES_DIR + "assets/notenoughmusic/"
LANG_DIR = ASSETS_DIR + "lang/"
MODELS_ITEM_DISCS_DIR = ASSETS_DIR + "models/item/discs/"
SOUNDS_MUSICS_DIR = ASSETS_DIR + "sounds/musics/"
TEXTURES_ITEM_DISCS_DIR = ASSETS_DIR + "textures/item/discs/"

def getArtistName(data):
    return ARTISTS[data["artist"]]["name"]

def getArtistPrefix(data):
    return ARTISTS[data["artist"]]["prefix"]

def getArtistRaw(data):
    return ARTISTS[data["artist"]]["raw"]

JUKEBOX_SONG = {
    "comparator_output": 15,
    "description": {
        "translate": "Artist - Title"
    },
    "length_in_seconds": 120.0,
    "sound_event": "notenoughmusic:nem_tenm_voyage"
}

# directly generate a file
def GenJukeboxSong(data):
    jukebox_song = dict(JUKEBOX_SONG)

    jukebox_song["comparator_output"] = data["comparatorOutput"]
    jukebox_song["description"]["translate"] = data["description"]
    jukebox_song["length_in_seconds"] = data["lengthInSeconds"]
    jukebox_song["sound_event"] = MOD_ID + ":" + NEM + getArtistName(data) + "_" + data["name"]

    # print(json.dumps(jukebox_song, indent=4))

    with open(JUKEBOX_DIR + getArtistPrefix(data) + data["name"] + JSON_SUFFIX, "w") as file:
        json.dump(jukebox_song, file, indent=2)



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
    SOUNDS[NEM + getArtistName(data) + "_" + data["name"]] = {
        "sounds": [
            {
                "name": MOD_ID + ":musics/" + getArtistPrefix(data) + data["name"],
                "stream": True
            }
        ]
    }

    # print(json.dumps(SOUNDS, indent=4))


LANG = {
    "item.notenoughmusic.discs.test.nem_test_music_disc": "Mod Maker",
}

# DO NOT directly generate the file
def GenLang(data):
    # lang parameter if used as target file
    LANG["item." + MOD_ID + ".discs." + getArtistRaw(data) + "." + NEM + getArtistName(data) + "_" + data["name"] + ITEM_DISCS_SUFFIX] = data["itemName"]

    # print(json.dumps(LANG, indent=4))


MODELS = {
    "parent": "minecraft:item/generated",
    "textures": {
        "layer0": "notenoughmusic:item/discs/test/nem_test"
    }
}

# directly generate a file
def GenModel(data):
    model = dict(MODELS)

    model["textures"]["layer0"] = MOD_ID + ":item/discs/" + getArtistPrefix(data) + data["name"]

    # print(json.dumps(model, indent=4))

    with open(MODELS_ITEM_DISCS_DIR + getArtistPrefix(data) + NEM + getArtistName(data) + "_" + data["name"] + ITEM_DISCS_SUFFIX + JSON_SUFFIX, "w") as f:
        json.dump(model, f, indent=2)



with open(DEFINITIONS, "r", encoding="utf-8") as file:
    data = json.load(file)

    for desc in data:
        GenJukeboxSong(desc)
        GenMainSounds(desc)
        GenLang(desc)
        GenModel(desc)

    with open(ASSETS_DIR + "sounds.json", "w") as sound:
        json.dump(SOUNDS, sound, indent=2)

    with open(LANG_DIR + "en_us.json", "w") as lang:
        json.dump(LANG, lang, indent=2)