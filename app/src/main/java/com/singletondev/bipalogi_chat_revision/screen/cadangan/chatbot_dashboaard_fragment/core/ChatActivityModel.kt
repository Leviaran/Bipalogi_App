package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core

import android.content.res.AssetManager
import com.singletondev.bipalogi_chat_revision.model.SmsDataModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.utils.SdCardChecked
import io.reactivex.Observable

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatActivityModel (var context: ChatBotDashboardFragment, var smsDataModel: SmsDataModel){

    fun provideSmsData() : SmsDataModel{
        return smsDataModel
    }

    fun isSdCardAvailable() : Observable<Boolean> {
        return SdCardChecked.isSdCardAvalaibleObservable(context.activity)
    }

    fun provideAssetManager() : AssetManager = context.activity.resources.assets



}