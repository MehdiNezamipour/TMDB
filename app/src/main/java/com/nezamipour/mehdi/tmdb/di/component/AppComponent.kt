package com.nezamipour.mehdi.tmdb.di.component

import com.nezamipour.mehdi.tmdb.di.module.AppModule
import com.nezamipour.mehdi.tmdb.di.module.RepositoryModule
import com.nezamipour.mehdi.tmdb.di.module.RetrofitModule
import com.nezamipour.mehdi.tmdb.view.fragment.DetailFragment
import com.nezamipour.mehdi.tmdb.view.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(detailFragment: DetailFragment)



}