package parsers


import fm.weigl.refdata.games.Team

import javax.inject.Inject

class TeamParser @Inject constructor() {

    companion object {
        private const val TEAMS_SEPARATOR = " - "
    }

    fun parseTeams(teamsText: String): Pair<Team, Team> {

        if (!teamsText.contains(TEAMS_SEPARATOR)) {
            throw IllegalArgumentException("Cant parse teams $teamsText")
        }

        val team0Name = teamsText.substring(0, teamsText.indexOf(TEAMS_SEPARATOR))
        val team1Name = teamsText.substring(teamsText.indexOf(TEAMS_SEPARATOR) + TEAMS_SEPARATOR.length, teamsText.length)

        val team0 = Team(team0Name)
        val team1 = Team(team1Name)


        return team0 to team1

    }

}