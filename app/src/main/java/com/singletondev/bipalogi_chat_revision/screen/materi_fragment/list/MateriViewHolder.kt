package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class MateriViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.item_textView_materi)
    lateinit var text_materi : TextView

    @BindView(R.id.item_button_materi)
    lateinit var button_materi : Button

    init {
        ButterKnife.bind(this,itemView)
    }
}