package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.roughike.bottombar.BottomBar
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.model.MateriDataModel
import com.singletondev.bipalogi_chat_revision.screen.ChatActivityDashboard
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.utils.SharedPref
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class MateriAdapter(var fragmentManager : Fragment,var bottomBar: BottomBar) : RecyclerView.Adapter<MateriViewHolder>() {

    companion object {
        const val MESSAGE_INTENT ="message_intent"
    }

    lateinit var listData: List<MateriDataModel>

    fun addRowMateri(listData : List<MateriDataModel>){
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriViewHolder {
        Log.e("jumlah",listData.size.toString())
        var view = parent.inflateFrom(R.layout.item_materi)
        return MateriViewHolder(view)
    }

    override fun onBindViewHolder(holder: MateriViewHolder, position: Int) {
        holder.text_materi.setText(listData[position].namaMateri)

        Log.e("shared",SharedPref.getInt(SharedPref.SCOREPERCOBAAN).toString())

        holder.button_materi.setOnClickListener {
            var bundle = Bundle()
            bundle.putString(MESSAGE_INTENT,listData[position].messageData)
            var chatBotDashBoardFragment = ChatBotDashboardFragment()
            chatBotDashBoardFragment.arguments = bundle
            SharedPref.saveNilaiInt(SharedPref.SCOREPERCOBAAN,SharedPref.getInt(SharedPref.SCOREPERCOBAAN)+1)

            bottomBar.selectTabAtPosition(0)
            fragmentManager.fragmentManager
                    .beginTransaction()
                    .replace(R.id.container,chatBotDashBoardFragment,ChatActivityDashboard.CHAT_DASHBOARD)
//                    .addToBackStack(ChatActivityDashboard.CHAT_DASHBOARD)
                    .commit()
        }

    }

    override fun getItemCount(): Int = listData.size

}