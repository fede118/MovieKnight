package com.section11.movieknight.core.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {

    fun io() : Scheduler {
        return Schedulers.io()
    }

    fun ui() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}