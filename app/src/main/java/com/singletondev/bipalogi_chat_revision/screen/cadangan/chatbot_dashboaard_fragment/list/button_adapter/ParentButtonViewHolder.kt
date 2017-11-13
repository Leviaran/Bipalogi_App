package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ParentButtonViewHolder(itemView: View,var buttonClickListener: ButtonClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(clicked: View) {
        buttonClickListener?.onItemClick(clicked,adapterPosition)
    }

    @BindView(R.id.btn)
    lateinit var button: Button

    init {
        ButterKnife.bind(this,itemView)
        button.setOnClickListener(this)
    }
}