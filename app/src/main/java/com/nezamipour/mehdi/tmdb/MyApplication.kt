package com.nezamipour.mehdi.tmdb

import android.app.Application
import com.nezamipour.mehdi.tmdb.di.component.AppComponent
import com.nezamipour.mehdi.tmdb.di.component.DaggerAppComponent
import com.nezamipour.mehdi.tmdb.di.module.AppModule
import com.nezamipour.mehdi.tmdb.di.module.RepositoryModule
import com.nezamipour.mehdi.tmdb.di.module.RetrofitModule

class MyApplication : Application() {

    companion object {
        private var component: AppComponent? = null
        fun getComponent(): AppComponent? {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent? {

        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .retrofitModule(RetrofitModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

}