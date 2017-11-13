package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger

import android.os.Environment
import com.singletondev.bipalogi_chat_revision.model.SmsDataModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityPresenter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@Module
        class ChatModule(var chatBotDashboardFragment: ChatBotDashboardFragment){

    @ChatScope
    @Provides
    fun provideView() : ChatActivityView {
        return ChatActivityView(chatBotDashboardFragment)
    }

    @ChatScope
    @Provides
    fun provideContext() : ChatBotDashboardFragment {
        return chatBotDashboardFragment
    }

    @ChatScope
    @Provides
    fun provideModel(smsDataModel: SmsDataModel) : ChatActivityModel {
        return ChatActivityModel(chatBotDashboardFragment,smsDataModel)
    }

    @ChatScope
    @Provides
    fun providePresenter(view : ChatActivityView, model : ChatActivityModel) : ChatActivityPresenter {
        var file = File(Environment.getExternalStorageDirectory().toString() + "/chatbot/bots/Rand");
        var compositeDisposable = CompositeDisposable()
        return ChatActivityPresenter(view,model,file,compositeDisposable)
    }

    @ChatScope
    @Provides
    fun provideSmsDataModel() : SmsDataModel {
        return SmsDataModel("coba")
    }
}