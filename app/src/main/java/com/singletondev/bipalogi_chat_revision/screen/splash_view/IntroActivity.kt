package com.singletondev.bipalogi_chat_revision.screen.splash_view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.splash_view.core.IntroActivityView
import com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger.DaggerIntroComponent
import com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger.IntroComponent
import com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger.IntroModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class IntroActivity : AppCompatActivity() {

    @Inject
    lateinit var introActivityView : IntroActivityView

    lateinit var component: IntroComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerIntroComponent.builder()
                .introModule(IntroModule(this))
                .build()
        component.inject(this)
        setContentView(introActivityView.provideGetView())
    }

    fun getComponents() : IntroComponent {
        return component
    }

    override fun onDestroy() {
        super.onDestroy()
        introActivityView.onDestroy()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


}