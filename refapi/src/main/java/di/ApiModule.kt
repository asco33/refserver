package di

import appversion.AppVersionServlet
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import gameslist.GamesServlet


@Module(injects = arrayOf(GamesServlet::class, AppVersionServlet::class))
class ApiModule {
    @Provides fun gson(): Gson = Gson()
}