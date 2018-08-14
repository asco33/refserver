package parsers


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GamedateParser @Inject constructor() {

    companion object {
        private val UHR = "Uhr"
    }


    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
            .apply { timeZone = TimeZone.getTimeZone("Europe/Berlin") }
    //    09.07.2016  15:00 Uhr

    @Throws(ParseException::class)
    fun parseDate(dateAndTimeText: String): Date {

        val cleanedDateAndTimeText = dateAndTimeText.replace(UHR, "").trim()

        return dateFormat.parse(cleanedDateAndTimeText)

    }
}