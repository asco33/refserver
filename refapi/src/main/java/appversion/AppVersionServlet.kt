package appversion

import ApiVersion.VERSION_KEY
import ApiVersion.VERSION_VALUE
import com.google.gson.Gson
import dagger.ObjectGraph
import di.ApiModule
import fm.weigl.refdata.appversion.AppVersion
import java.io.IOException
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AppVersionServlet : HttpServlet() {

    companion object {
        const val LATEST_APP_VERION_CODE = 0
        const val LATEST_APP_VERSION_NAME = "test"
        const val LATEST_APP_VERSION_FEATURES = "description"
        const val UTF = "utf-8"
        const val CONTENT_TYPE_JSON = "application/json"
    }

    @Inject lateinit var gson: Gson

    val appVersion = AppVersion(LATEST_APP_VERION_CODE, LATEST_APP_VERSION_NAME, LATEST_APP_VERSION_FEATURES)

    init {
        ObjectGraph.create(ApiModule()).inject(this)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        resp.characterEncoding = UTF
        resp.addHeader(VERSION_KEY, VERSION_VALUE)

        try {
            resp.contentType = CONTENT_TYPE_JSON
            resp.setStatus(HttpServletResponse.SC_OK)
            resp.writer.print(gson.toJson(appVersion))
        } catch (e: Exception) {
            e.printStackTrace()
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            resp.writer.print(e.toString())
        }

        resp.writer.flush()

    }

}
