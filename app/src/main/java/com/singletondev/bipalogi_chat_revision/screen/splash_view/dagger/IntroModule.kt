package com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger

import com.singletondev.bipalogi_chat_revision.screen.splash_view.IntroActivity
import com.singletondev.bipalogi_chat_revision.screen.splash_view.core.IntroActivityView
import dagger.Module
import dagger.Provides

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@Module
class IntroModule(var introActivity: IntroActivity){

    @Provides
    fun provideContext() : IntroActivity = introActivity

    @Provides
    fun provideView() : IntroActivityView {
        return IntroActivityView(introActivity)
    }
}