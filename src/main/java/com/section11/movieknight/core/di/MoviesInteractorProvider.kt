package com.section11.movieknight.core

import android.content.Context
import com.section11.movieknight.core.di.SchedulerProvider
import com.section11.movieknight.interactor.CoroutineImdbMoviesInteractor
import com.section11.movieknight.interactor.MovieKnightInteractor
import com.section11.movieknight.interactor.RxJavaImDbMoviesInteractor
import com.section11.movieknight.service.ComingSoonMoviesService
import com.section11.movieknight.service.ComingSoonMoviesSuspendService
import com.section11.movieknight.service.InTheatersMoviesService
import com.section11.movieknight.service.InTheatersMoviesSuspendService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ServicesEntryPoint {
    fun getCoroutineInTheatersService(): InTheatersMoviesSuspendService
    fun getCoroutineComingSoonService(): ComingSoonMoviesSuspendService
    fun getRxJavaInTheatersService(): InTheatersMoviesService
    fun getRxJavaComingSoonService(): ComingSoonMoviesService
}

object InteractorsProvider {

    private lateinit var entryPoint: ServicesEntryPoint

    fun getInteractor(context: Context, coroutineFeatureFlag: Boolean): MovieKnightInteractor {
        entryPoint = EntryPointAccessors.fromApplication(context, ServicesEntryPoint::class.java)
        return if (coroutineFeatureFlag) {
            provideCoroutineInteractor()
        } else {
            provideRxJavaInteractor()
        }
    }

    private fun provideCoroutineInteractor(): CoroutineImdbMoviesInteractor {
        return CoroutineImdbMoviesInteractor(
            entryPoint.getCoroutineComingSoonService(),
            entryPoint.getCoroutineInTheatersService()
        )
    }

    private fun provideRxJavaInteractor(): RxJavaImDbMoviesInteractor {
        return RxJavaImDbMoviesInteractor(
            entryPoint.getRxJavaComingSoonService(),
            entryPoint.getRxJavaInTheatersService(),
            SchedulerProvider()
        )
    }
}
