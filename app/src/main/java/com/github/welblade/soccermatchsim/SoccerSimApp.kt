package com.github.welblade.soccermatchsim

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.github.welblade.soccermatchsim.core.CrashlyticsReportingTree
import com.github.welblade.soccermatchsim.data.di.DataModules
import com.github.welblade.soccermatchsim.domain.di.DomainModules
import com.github.welblade.soccermatchsim.ui.di.PresentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber.*
import timber.log.Timber.Forest.plant

class SoccerSimApp : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        } else {
            plant(CrashlyticsReportingTree())
        }
        startKoin {
            androidContext(this@SoccerSimApp)
        }
        DataModules.load()
        DomainModules.load()
        PresentationModules.load()
    }
}