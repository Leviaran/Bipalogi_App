package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityPresenter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.ChatModule
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.DaggerChatComponent
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonClickListener
import javax.inject.Inject

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class SubViewHolderItemKiri(item: View,var listString : MutableList<String>,var chatBotDashboardFragment: ChatBotDashboardFragment) : ParentViewHolder(item), ButtonClickListener{



    @BindView(R.id.text_view_isi_pesan_item_kiri)
    lateinit var textViewPesanItemKiri: TextView

//    var stringData = arrayOf("Thailand","Myanmar", "Malay", "indo")

    init {
        ButterKnife.bind(this, item)
//        listString.addAll(stringData)
//        Log.e("coba","coba")
//        Log.e("list",listString.size.toString())

    }

    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(context,"Kamu klik ${listString[position]}",Toast.LENGTH_LONG).show()


    }

}