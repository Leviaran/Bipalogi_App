package com.singletondev.bipalogi_chat_revision.applicationBuilder

import android.app.Application
import android.content.Context
import android.util.Log
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.utils.SharedPref
import com.squareup.leakcanary.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.HashMap

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class AppController : Application() {
    lateinit var refWatcher : RefWatcher

    override fun onCreate() {
        context = applicationContext
        super.onCreate()
        refWatcher = LeakCanary.install(this)
        Log.e("mencoba","coba")

        //inject with Caligraphy font
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("font/sanfran.otf")
                .setFontAttrId(R.attr.fontPath)
                .build())


    }

    companion object {
        fun getRefWatcher(context : Context): RefWatcher {
            var application : AppController = context.applicationContext as AppController
            return application.refWatcher
        }

        @get:Synchronized
        var context : Context? =null

    }

    private fun initializationLogging(){
        when {
            BuildConfig.DEBUG -> {
                Timber.plant(Timber.DebugTree())
            }
            else -> {
                Timber.plant(object : Timber.Tree() {
                    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
                        Log.e("Yeah","Berhasil")
                    }
                })
            }
        }
    }

}