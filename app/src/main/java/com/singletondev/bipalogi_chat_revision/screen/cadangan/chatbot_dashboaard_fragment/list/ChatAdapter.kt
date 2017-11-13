package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.model.SmsDataModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityPresenter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.ChatModule
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.dagger.DaggerChatComponent
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonClickListener
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.hookClickListenerFromButton
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom
import javax.inject.Inject

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatAdapter(var chatBotDashboardFragment: ChatBotDashboardFragment) : RecyclerView.Adapter<ParentViewHolder>(), ButtonClickListener {

    @Inject
    lateinit var chatActivityPresenter : ChatActivityPresenter

    companion object {
        const val TYPE_KANAN = 1
        const val TYPE_KIRI = 2
    }

    private lateinit var listViewType : List<Int>
    private lateinit var listDataSms : List<SmsDataModel>
    lateinit var containerButtonRecyclerView : RecyclerView
    var listString : MutableList<String> = ArrayList()
    val numberOfCollumn = 2
//    var buttonAdapter = ButtonAdapter()

    fun insertDataMessage(listViewType: List<Int>, listDataSms: List<SmsDataModel>){
        this.listViewType = listViewType
        this.listDataSms = listDataSms
    }

//    fun insertButtonString(listString : MutableList<String>){
//        this.listString = listString
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

//        DaggerChatComponent.builder()
//                .chatModule(ChatModule(chatBotDashboardFragment))
//                .build()
//                .inject(this)

        when (viewType) {
            TYPE_KANAN -> return SubViewHolderItemKanan(parent.inflateFrom(R.layout.item_kanan))
            else -> return SubViewHolderItemKiri(parent.inflateFrom(R.layout.item_kiri),listString, chatBotDashboardFragment)
        }
    }

    override fun onBindViewHolder(holder: ParentViewHolder?, position: Int) {
        var viewTypePosition = listViewType.get(position)
        var dataSms = listDataSms.get(position)
        when (viewTypePosition){
            TYPE_KANAN -> {
                var holderKanan = holder as SubViewHolderItemKanan
                holderKanan.textViewPesanItemKanan.text = dataSms.smsMessage
            }

            else -> {
                var holderKiri = holder as SubViewHolderItemKiri
                holderKiri.textViewPesanItemKiri.text = dataSms.smsMessage
//                when {
//                    listString.size != 0 -> {
//                        buttonAdapter.updateStringButtonList(listString)
//                        Log.e("jumlah listItem",listString.get(0))
//                        holderKiri.containerButtonRecyclerView.visibility = View.VISIBLE
//                        holderKiri.containerButtonRecyclerView.layoutManager = GridLayoutManager(chatBotDashboardFragment.context,numberOfCollumn)
//                        buttonAdapter.setClickListener(this)
//
//                        holderKiri.containerButtonRecyclerView.adapter = buttonAdapter
//
//                    }
//                }
            }
        }
    }

    override fun getItemCount(): Int = listViewType.size

    override fun getItemViewType(position: Int): Int = listViewType.get(position)

    override fun onItemClick(view: View, position: Int) {
//        Log.e("List",listString[position])
//        Toast.makeText(chatBotDashboardFragment.context, position,Toast.LENGTH_SHORT).show()
//        hookeClickListenerFromButton.provideStringListener(listString[position])
    }


}