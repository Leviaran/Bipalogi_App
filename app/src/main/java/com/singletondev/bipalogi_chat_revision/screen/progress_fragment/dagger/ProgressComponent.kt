package com.singletondev.bipalogi_chat_revision.screen.progress_fragment.dagger

import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.ProgressFragment
import dagger.Component

/**
 * Created by Randy Arba on 11/12/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@ProgressScope
        @Component(modules = arrayOf(ProgressModule::class))
        interface ProgressComponent{
    fun inject(progressFragment: ProgressFragment)
}