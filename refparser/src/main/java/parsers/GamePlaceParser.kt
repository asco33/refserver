package parsers


import fm.weigl.refdata.games.GamePlace

import javax.inject.Inject

class GamePlaceParser @Inject constructor() {

    fun parseGamePlace(placeStr: String): GamePlace {
        return GamePlace(placeStr)
    }
}
