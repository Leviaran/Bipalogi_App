package com.singletondev.bipalogi_chat_revision.screen.progress_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.presenter.ProgressPresenter
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.view.ProgressView
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.dagger.DaggerProgressComponent
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.dagger.ProgressModule
import javax.inject.Inject

/**
 * Created by Randy Arba on 11/12/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ProgressFragment : Fragment() {

    @Inject
    lateinit var progressView : ProgressView

    @Inject
    lateinit var progressPresenter : ProgressPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerProgressComponent.builder()
                .progressModule(ProgressModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        progressPresenter.onCreate()
        return progressView.provideView()
    }
}