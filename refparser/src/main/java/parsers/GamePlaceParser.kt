package parsers


import fm.weigl.refdata.GamePlace

import javax.inject.Inject

/**
 * Created by asco on 09.07.16.
 */
class GamePlaceParser @Inject constructor() {

    fun parseGamePlace(placeStr: String): GamePlace {
        return GamePlace(placeStr)
    }
}
