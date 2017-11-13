package com.singletondev.bipalogi_chat_revision.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class PrefManager (var context: Context) {

    //Init sharedPreferences file name
    companion object {
        const val PREF_NAME : String = "Bipalogi"
        const val IS_FIRSTIME : String = "YES"
        //Shared pref level Mode
        const val PRIVATE_MODE = 0
    }



    private var sharedPref : SharedPreferences
    private var sharedPrefEdit : SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        sharedPrefEdit = sharedPref.edit()
    }

    fun setFirstTimeLaunch(isFirstTime : Boolean) {
        sharedPrefEdit.putBoolean(IS_FIRSTIME, isFirstTime)
        sharedPrefEdit.commit()
    }

    fun isFirstTimeLaunch() : Boolean {
        return sharedPref.getBoolean(PREF_NAME,true)
    }
}