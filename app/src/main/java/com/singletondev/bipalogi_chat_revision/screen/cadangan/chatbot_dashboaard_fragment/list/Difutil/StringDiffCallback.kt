package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.Difutil

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import android.util.Log

/**
 * Created by Randy Arba on 11/7/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class StringDiffCallback (var oldString: MutableList<String>?,var newString : MutableList<String>) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldString?.get(oldItemPosition).equals(newString.get(newItemPosition))
    }

    override fun getOldListSize(): Int {
        var jumlah = oldString?.size ?: 0
        return jumlah
    }

    override fun getNewListSize(): Int = newString.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldStringObjek = oldString?.get(oldItemPosition)
        val newStringObjek = newString.get(newItemPosition)
//        Log.e("hasilnya",oldStringObjek + " dengan " + newStringObjek)

        return oldStringObjek.equals(newStringObjek)
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}