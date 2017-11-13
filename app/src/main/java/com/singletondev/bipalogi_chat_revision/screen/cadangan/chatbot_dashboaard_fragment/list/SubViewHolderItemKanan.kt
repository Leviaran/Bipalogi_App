package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R


/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class SubViewHolderItemKanan(item: View) : ParentViewHolder(item){


    @BindView(R.id.text_view_isi_pesan_item_kanan)
    lateinit var textViewPesanItemKanan: TextView
    init {
        ButterKnife.bind(this, item)
    }
}