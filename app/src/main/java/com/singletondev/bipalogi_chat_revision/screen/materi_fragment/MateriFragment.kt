package com.singletondev.bipalogi_chat_revision.screen.materi_fragment

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.roughike.bottombar.BottomBar
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.ChatActivityDashboard
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.core.MateriView
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.dagger.DaggerMateriComponent
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.dagger.MateriModule
import javax.inject.Inject


/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class MateriFragment : Fragment() {

    @Inject
    lateinit var materiView : MateriView
//    val chatActivityDashboard: ChatActivityDashboard by lazy {
//        ChatActivityDashboard()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bottomBar : BottomBar = activity.findViewById(R.id.bottom_bar)
        DaggerMateriComponent.builder()
                .materiModule(MateriModule(this,bottomBar))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return materiView.provideView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            (resultCode != TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) -> {
                var installTTSIntent = Intent()
                installTTSIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                startActivity(installTTSIntent)
            }
        }
    }



}