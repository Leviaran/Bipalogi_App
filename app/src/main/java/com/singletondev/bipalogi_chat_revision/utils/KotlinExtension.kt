package com.singletondev.bipalogi_chat_revision.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

fun ViewGroup.inflateFrom(layoutId : Int, flag : Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutId,this,flag)
}