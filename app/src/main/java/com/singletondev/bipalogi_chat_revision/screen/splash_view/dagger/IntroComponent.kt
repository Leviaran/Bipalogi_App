package com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger

import com.singletondev.bipalogi_chat_revision.screen.splash_view.IntroActivity
import com.singletondev.bipalogi_chat_revision.screen.splash_view.fragment.IntroFragment
import dagger.Component

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@Component(modules = arrayOf(IntroModule::class))
interface IntroComponent {
    fun inject(introActivity: IntroActivity)
    fun inject(introFragment: IntroFragment)
}