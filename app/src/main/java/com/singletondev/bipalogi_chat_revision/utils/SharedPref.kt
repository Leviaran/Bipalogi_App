package com.singletondev.bipalogi_chat_revision.utils

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.singletondev.bipalogi_chat_revision.MainActivity
import com.singletondev.bipalogi_chat_revision.applicationBuilder.AppController

/**
 * Created by Randy Arba on 11/13/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class SharedPref {
    companion object {

        const val SCOREVOCAB = "score_vocab"
        const val SCOREMATERI = "score_materi"
        const val SCOREPERCOBAAN = "score_percobaan"

        private fun getPref() : SharedPreferences {
            var context = AppController.context
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun getEditor(): SharedPreferences.Editor = getPref().edit()

        fun saveNilaiString(key : String, nilai : String){
            getEditor().putString(key,nilai).commit()
        }

        fun saveNilaiInt(key : String, nilai : Int){
            getEditor().putInt(key,nilai).commit()
        }

        fun getString(key : String): String = getPref().getString(key,null)
        fun getInt(key: String): Int = getPref().getInt(key,0)
    }
}