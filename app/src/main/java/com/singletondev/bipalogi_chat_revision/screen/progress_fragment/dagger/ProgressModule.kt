package com.singletondev.bipalogi_chat_revision.screen.progress_fragment.dagger

import android.os.Build
import android.support.annotation.RequiresApi
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.ProgressFragment
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.presenter.ProgressPresenter
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.view.ProgressView
import dagger.Module
import dagger.Provides

/**
 * Created by Randy Arba on 11/12/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@Module
        class ProgressModule(var progressFragment: ProgressFragment){

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @ProgressScope
    @Provides
    fun provideView() : ProgressView{
        return ProgressView(progressFragment)
    }

    @ProgressScope
    @Provides
    fun providePresenter(progressView : ProgressView) : ProgressPresenter {
        return ProgressPresenter(progressView)
    }
}