package di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides


@Module
class ApiModule {
    @Provides
    fun gson(): Gson = Gson()
}