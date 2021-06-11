package com.nezamipour.mehdi.tmdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {


    // for dagger 2
/*    companion object {
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
        return DaggerAppComponent.create()

    }*/

}