package com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.presenter

import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.view.ProgressView
import com.singletondev.bipalogi_chat_revision.utils.SharedPref

/**
 * Created by Randy Arba on 11/13/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ProgressPresenter(
        var progressView: ProgressView
){

    fun onCreate(){
        initTextScore()
    }
    fun initTextScore(){
        progressView.score_mencoba_jawab.text = SharedPref.getInt(SharedPref.SCOREPERCOBAAN).toString()
        progressView.score_vocab.text = SharedPref.getInt(SharedPref.SCOREVOCAB).toString()
    }
}