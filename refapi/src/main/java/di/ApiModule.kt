package di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import etc.DateAdapter
import etc.DateFormat
import java.util.*


@Module
class ApiModule {
    @Provides
    fun gson(): Gson = GsonBuilder().registerTypeAdapter(Date::class.java, DateAdapter()).create()
}