package parsers

import fm.weigl.refdata.games.Game
import fm.weigl.refdata.games.GameReferee
import fm.weigl.refdata.games.Games
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import tools.JsoupTool
import java.io.IOException
import java.util.*
import javax.inject.Inject

class RefParserMain @Inject constructor(
        private val teamParser: TeamParser,
        private val dateParser: GamedateParser,
        private val refereeParser: GameRefereeParser,
        private val placeParser: GamePlaceParser,
        private val jsoupTool: JsoupTool
) {

    companion object {
        private val URL = "http://refbb.de/crews.html"
        private val TABLE = "table"
        private val TR = "tr"
        private val TABLE_ROW_TEAMS = 0
        private val TABLE_ROW_GAMEDAY = 1
        private val TABLE_ROW_GAMEPLACE = 2
        private val TABLE_ROW_REFS_START = 3
    }


    @Throws(Exception::class)
    fun games() = parseGames()

    @Throws(IOException::class)
    private fun parseGames(): Games {

        val doc = Jsoup.connect(URL).get()

        val tables = getTables(doc)

        val gamesList = tables.mapNotNull {
            try {
                parseGameRows(it.getElementsByTag(TR))
            } catch (e1: Exception) {
                e1.printStackTrace()
                null
            }
        }

        return Games(gamesList)

    }

    private fun getTables(doc: Document): List<Element> {
        return doc.getElementsByTag(TABLE)
    }

    @Throws(Exception::class)
    private fun parseGameRows(rows: List<Element>): Game {

        val teamsText = jsoupTool.textFromElement(rows[TABLE_ROW_TEAMS])
        val teams = teamParser.parseTeams(teamsText)

        val dateText = jsoupTool.textFromElement(rows[TABLE_ROW_GAMEDAY])

        val gameDay = dateParser.parseDate(dateText)

        val gamePlaceStr = jsoupTool.textFromElement(rows[TABLE_ROW_GAMEPLACE])

        val gamePlace = placeParser.parseGamePlace(gamePlaceStr)

        val referees = ArrayList<GameReferee>()

        for (i in TABLE_ROW_REFS_START until rows.size) {

            val refString = jsoupTool.textFromElement(rows[i])

            val referee = refereeParser.parseReferee(refString)
            referees.add(referee)
        }

        return Game(referees, gameDay, gamePlace, teams.first, teams.second)
    }
}
