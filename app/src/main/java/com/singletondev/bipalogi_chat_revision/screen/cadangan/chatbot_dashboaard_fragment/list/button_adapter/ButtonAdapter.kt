package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter

import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.Difutil.StringDiffCallback
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ButtonAdapter() : RecyclerView.Adapter<ParentButtonViewHolder>() {
    var listButton: MutableList<String> = ArrayList<String>()
    var flag : Boolean = false

    fun insertData(listButton: MutableList<String>){
        this.listButton = listButton
    }

    private lateinit var buttonClickListener : ButtonClickListener

    fun setClickListener(buttonClickListener: ButtonClickListener){
        this.buttonClickListener = buttonClickListener
    }

    //method for diffutil update data
    fun updateStringButtonList(stringButton : MutableList<String>) {
        Log.e("JUMLAH",stringButton.size.toString())
        val diffCallback = StringDiffCallback(listButton, stringButton)
        val difResult = DiffUtil.calculateDiff(diffCallback)

        if (!this.listButton.isEmpty())
        {
            Log.e("listButton",listButton.size.toString())
            if (!diffCallback.areContentsTheSame(0,0)){
                if (stringButton.size == 1) {
                    this.listButton.clear()
                    difResult.dispatchUpdatesTo(this)
                } else {
                    Log.e("halo","halo")
                    this.listButton.clear()
                    this.listButton.addAll(stringButton)
                    difResult.dispatchUpdatesTo(this)
                }
            } else if (stringButton.size == 0) {
                Log.e("halo","halo2")
                this.listButton.clear()
                difResult.dispatchUpdatesTo(this)
            } else {
                Log.e("halo","halo3")
                this.listButton.clear()
                this.listButton.addAll(stringButton)
                difResult.dispatchUpdatesTo(this)
            }
        } else if (stringButton.size == 1) {
            Log.e("halo","halo2")
            this.listButton.clear()
            difResult.dispatchUpdatesTo(this)
        } else {
            Log.e("halo","halo4")
            this.listButton.addAll(stringButton)
            difResult.dispatchUpdatesTo(this)
//            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ParentButtonViewHolder?, position: Int) {
        if (listButton.size != 0)
        {
            holder?.button?.text = listButton.get(position)
            Log.e("position",position.toString())
            when {
                position%2!=0 ->
                {
                    holder?.button?.setTextColor(Color.parseColor("#000000"))
                    holder?.button?.setBackgroundResource(R.drawable.button_no_background)
                } else -> {
                holder?.button?.setTextColor(Color.parseColor("#ffffff"))
                holder?.button?.setBackgroundResource(R.drawable.button_yes_background)
            }
            }
        }
    }

    override fun getItemCount(): Int {
        return listButton.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentButtonViewHolder {
        var view : View = parent.inflateFrom(R.layout.button)
        return ParentButtonViewHolder(view,buttonClickListener)
    }

}