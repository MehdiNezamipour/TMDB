package com.nezamipour.mehdi.tmdb.di.module

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.nezamipour.mehdi.tmdb.data.local.AppDatabase
import com.nezamipour.mehdi.tmdb.data.local.MovieDao
import com.nezamipour.mehdi.tmdb.data.local.MovieRemoteKeyDao
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.data.remote.Routes
import com.nezamipour.mehdi.tmdb.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): MovieRepository {
        return MovieRepository(apiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "movieDB.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.getMovieDao()
    }

    @Provides
    @Singleton
    fun provideMovieRemoteKeyDao(appDatabase: AppDatabase): MovieRemoteKeyDao {
        return appDatabase.getMovieRemoteKeyDao()
    }


}

