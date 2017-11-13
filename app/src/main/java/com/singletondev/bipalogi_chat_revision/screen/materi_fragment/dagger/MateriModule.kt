package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.dagger

import android.os.Build
import android.support.annotation.RequiresApi
import com.roughike.bottombar.BottomBar
import com.singletondev.bipalogi_chat_revision.model.MateriDataModel
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.MateriFragment
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.core.MateriView
import dagger.Module
import dagger.Provides

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@Module
        class MateriModule(var materiFragment: MateriFragment,var bottomBar: BottomBar){

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @MateriScope
    @Provides
    fun provideView() : MateriView {
        return MateriView(materiFragment,bottomBar)
    }

    @MateriScope
    @Provides
    fun provideContext() : MateriFragment {
        return materiFragment
    }

    @MateriScope
    @Provides
    fun provideMateriDataModel() : MateriDataModel = MateriDataModel("coba","coba")
}