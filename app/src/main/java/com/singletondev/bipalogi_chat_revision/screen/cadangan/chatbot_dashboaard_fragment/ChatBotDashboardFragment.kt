package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.singletondev.bipalogi_chat_revision.applicationBuilder.AppController
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityPresenter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityView
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.ChatModule
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.DaggerChatComponent
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.list.MateriAdapter
import com.squareup.leakcanary.RefWatcher
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Randy Arba on 11/4/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatBotDashboardFragment : Fragment() {
    @Inject
    lateinit var chatActivityView : ChatActivityView

    @Inject
    lateinit var chatActivityPresenter : ChatActivityPresenter

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.e("inflate","inflate")
        return chatActivityView.provideGetView()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerChatComponent.builder()
                .chatModule(ChatModule(this))
                .build()
                .inject(this)

        chatActivityView.onCreate(chatActivityPresenter)
        chatActivityPresenter.onCreate()
        chatActivityView.initAdapter()

        //recieve Intent from Materi Fragment
        var bundle = arguments
        if (bundle != null)
        {
            chatActivityPresenter.hookIntoMessage(bundle.getString(MateriAdapter.MESSAGE_INTENT))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        chatActivityPresenter.onDestroy()
        chatActivityView.onDestroy()
        var refWatcher: RefWatcher = AppController.getRefWatcher(this.activity)
        refWatcher.watch(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        chatActivityPresenter.onDestroy()
        chatActivityView.onDestroy()
        var refWatcher: RefWatcher = AppController.getRefWatcher(this.activity)
        refWatcher.watch(this)
    }

    override fun onDetach() {
        super.onDetach()
        chatActivityPresenter.onDestroy()
        chatActivityView.onDestroy()
        var refWatcher: RefWatcher = AppController.getRefWatcher(this.activity)
        refWatcher.watch(this)

    }
}