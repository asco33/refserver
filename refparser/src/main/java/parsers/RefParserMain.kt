package parsers

import fm.weigl.refdata.Game
import fm.weigl.refdata.GameReferee
import fm.weigl.refdata.Games
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import tools.JsoupTool
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * Created by asco on 08.07.16.
 */
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
    }


    @Throws(Exception::class) fun games() = parseGames()

    @Throws(IOException::class)
    private fun parseGames(): Games {

        val gameList = ArrayList<Game>()

        val doc = Jsoup.connect(URL).get()

        val tables = getTables(doc)

        for (i in tables.indices) {

            if (i == 0) continue

            val e = tables[i]

            try {
                val game = parseGameRows(e.getElementsByTag(TR))
                gameList.add(game)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        }

        return Games(gameList)

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

        for (i in 3..rows.size - 1) {

            val refString = jsoupTool.textFromElement(rows[i])

            val referee = refereeParser.parseReferee(refString)
            referees.add(referee)
        }

        val game = Game(referees, gameDay, gamePlace, teams[0], teams[1])

        return game

    }

}
