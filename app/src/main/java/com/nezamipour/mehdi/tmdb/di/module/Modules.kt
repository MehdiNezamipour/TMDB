package com.nezamipour.mehdi.tmdb.di.module

import android.app.Application
import com.google.gson.*
import com.nezamipour.mehdi.tmdb.data.model.Movie
import com.nezamipour.mehdi.tmdb.data.model.repository.MovieRepository
import com.nezamipour.mehdi.tmdb.network.ApiService
import com.nezamipour.mehdi.tmdb.network.Routes
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Routes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): MovieRepository {
        return MovieRepository(apiService)
    }
}

@Module
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }
}
