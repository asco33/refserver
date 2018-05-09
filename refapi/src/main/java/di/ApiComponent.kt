package di

import appversion.AppVersionServlet
import dagger.Component
import gameslist.GamesServlet

@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {
    fun inject(servlet: GamesServlet)
    fun inject(servlet: AppVersionServlet)
}