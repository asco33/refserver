package testgames

import fm.weigl.refdata.games.*
import java.util.*

class TestGames {

    fun testGames(): Games {
        val ref1 = GameReferee("Walter Whitehat", "R")
        val ref2 = GameReferee("Ulrike Umpire", "U")
        val ref3 = GameReferee("Linus Linejudge", "LJ")
        val ref4 = GameReferee("Laura Linesmen", "LM")
        val ref5 = GameReferee("Benjamin Backjudge", "BJ")
        val referees1 = listOf(ref1, ref2, ref3, ref4, ref5)
        val place1 = GamePlace("Stadion der Weltjugend")
        val homeTeam1 = Team("Tegel Tapirs")
        val awayTeam1 = Team("Wedding Weasels")
        val game1 = Game(referees1, Date(), place1, homeTeam1, awayTeam1)

        val ref6 = GameReferee("Wiebke Whitehat", "R")
        val ref7 = GameReferee("Urs Umpire", "U")
        val ref8 = GameReferee("Lisa Linejudge", "LJ")
        val ref9 = GameReferee("Lars Linesmen", "LM")
        val ref10 = GameReferee("Brigitte Backjudge", "BJ")
        val referees2 = listOf(ref6, ref7, ref8, ref9, ref10)
        val place2 = GamePlace("Stadion am Gesundbrunnen")
        val homeTeam2 = Team("Pankow Parrots")
        val awayTeam2 = Team("Weissensee Whales")
        val game2 = Game(referees2, Date(), place2, homeTeam2, awayTeam2)
        return Games(listOf(game1, game2))
    }

}