package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger

import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.ChatAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.SubViewHolderItemKiri
import dagger.Component

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@ChatScope
        @Component(modules = arrayOf(ChatModule::class))
        interface ChatComponent {
    fun inject(chatBotDashboardFragment: ChatBotDashboardFragment)
}