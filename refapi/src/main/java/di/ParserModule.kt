package di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import servlet.GamesServlet


/**
 * Created by asco on 09.07.16.
 */

@Module(injects = arrayOf(GamesServlet::class))
class ParserModule {
    @Provides fun gson(): Gson = Gson()
}