package servlet

import com.google.gson.Gson
import dagger.ObjectGraph
import di.ParserModule
import parsers.RefParserMain
import java.io.IOException
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by asco on 13.07.16.
 */
class GamesServlet : HttpServlet() {

    companion object {
        const val VERSION_KEY = "version"
        const val VERSION_VALUE = "1.0/080917"
        const val UTF = "utf-8"
        const val CONTENT_TYPE_JSON = "application/json"
        const val CONTENT_TYPE_TEXT = "text/plain"
        const val AUTH_HEADER = "Authorization"
        const val NOT_AUTHED_MSG = "Nope"
    }

    @Inject lateinit var refParser: RefParserMain
    @Inject lateinit var gson: Gson

    init {
        ObjectGraph.create(ParserModule()).inject(this)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        resp.characterEncoding = UTF
        resp.addHeader(VERSION_KEY, VERSION_VALUE)

        val authHeader = req.getHeader(AUTH_HEADER)


//        if (authHeader == null || authHeader != ApiKey.API_KEY) { // do not demand an api key for now
        if (false) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN)
            resp.contentType = CONTENT_TYPE_TEXT
            resp.writer.print(NOT_AUTHED_MSG)

        } else
            try {
                val games = refParser.games()
                val serializedGames = gson.toJson(games)
                resp.contentType = CONTENT_TYPE_JSON
                resp.setStatus(HttpServletResponse.SC_OK)
                resp.writer.print(serializedGames)
            } catch (e: Exception) {
                e.printStackTrace()
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                resp.writer.print(e.toString())
            }

        resp.writer.flush()

    }

}
