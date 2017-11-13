package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.dagger

import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.MateriFragment
import dagger.Component

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@MateriScope
        @Component(modules = arrayOf(MateriModule::class))
        interface MateriComponent{
    fun inject(materiFragment: MateriFragment)
}