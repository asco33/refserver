package fm.weigl.refdata.games

class GameReferee(val name: String, position: String) {
    var position: RefereePosition? = null

    init {

        if (position == RefereePosition.R.toString()) {
            this.position = RefereePosition.R
        } else if (position == RefereePosition.U.toString()) {
            this.position = RefereePosition.U
        } else if (position == RefereePosition.LJ.toString()) {
            this.position = RefereePosition.LJ
        } else if (position == RefereePosition.LM.toString()) {
            this.position = RefereePosition.LM
        } else if (position == RefereePosition.FJ.toString()) {
            this.position = RefereePosition.FJ
        } else if (position == RefereePosition.SJ.toString()) {
            this.position = RefereePosition.SJ
        } else if (position == RefereePosition.BJ.toString()) {
            this.position = RefereePosition.BJ
        } else if (position == RefereePosition.GC.toString()) {
            this.position = RefereePosition.GC
        } else if (position == RefereePosition.B.toString()) {
            this.position = RefereePosition.B
        } else {
            throw IllegalArgumentException("unkown position " + position)
        }

    }
}
