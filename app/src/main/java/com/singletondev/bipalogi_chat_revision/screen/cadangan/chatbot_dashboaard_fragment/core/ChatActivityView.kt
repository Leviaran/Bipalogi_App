package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.model.SmsDataModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.ChatAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonClickListener
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.hookClickListenerFromButton
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatActivityView(var chatBotDashboardFragment: ChatBotDashboardFragment) : ButtonClickListener{

    override fun onItemClick(view: View, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var view: View
    lateinit var chatAdapter: ChatAdapter
    var compositeDisposable = CompositeDisposable()
    @BindView(R.id.recycler_view_main)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.container_button_menu_utama)
    lateinit var containerRecyclerViewButton : RecyclerView

    @BindView(R.id.text_view_isi_message)
    lateinit var textIsiMessage: EditText

    @BindView(R.id.fab_message_kirim)
    lateinit var fab: FloatingActionButton

    lateinit var textToSpeech : TextToSpeech

    init {
        var frameLayout = FrameLayout(chatBotDashboardFragment.activity)
        frameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        view = frameLayout.inflateFrom(R.layout.fragment_chatbot_dashboard,true)
        ButterKnife.bind(this,view)
    }

    fun onCreate(chatActivityPresenter: ChatActivityPresenter){
        chatAdapter = ChatAdapter(chatBotDashboardFragment)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initAdapter(){
        recyclerView.adapter = chatAdapter
        var layoutManager = LinearLayoutManager(chatBotDashboardFragment.activity)
        recyclerView.layoutManager = layoutManager

        //Keyboard AttachView
        compositeDisposable.add(settingKeyboardAttachView(recyclerView,chatAdapter))

        var sdk = Build.VERSION.SDK_INT
        if(sdk>=Build.VERSION_CODES.LOLLIPOP){
            //init status bar color
            var window = chatBotDashboardFragment.activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = chatBotDashboardFragment.resources.getColor(R.color.colorStatusBar1)
        }

        //Animate RecyclerView
        var itemAnimator = DefaultItemAnimator()
        itemAnimator.addDuration = 1000
        itemAnimator.removeDuration = 1000
        recyclerView.itemAnimator = itemAnimator
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }

    fun provideChatAdapter() : ChatAdapter = chatAdapter


    fun settingKeyboardAttachView(recyclerView: RecyclerView,chatAdapter: ChatAdapter) : Disposable{
        Log.e("keyboard","set")
        return Observable.just(
                recyclerView.viewTreeObserver.addOnGlobalLayoutListener {
                    val r = Rect()
                    recyclerView.getWindowVisibleDisplayFrame(r)
                    val screenHeight = recyclerView.rootView.height
                    val keyboardHeight = screenHeight - r.bottom
                    when {
                        keyboardHeight > screenHeight * 0.15 -> {
                            recyclerView.smoothScrollToPosition(chatAdapter.itemCount)
                        }
                    }
                }
        ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
        }


    fun provideContext() : Context {
        return chatBotDashboardFragment.activity
    }

    fun refreshDataList(){
        recyclerView.smoothScrollToPosition(chatAdapter.itemCount)
        chatAdapter.notifyDataSetChanged()
    }

    fun provideGetView(): View = view

    fun provideTextToSpeech(): TextToSpeech {
        textToSpeech = TextToSpeech(chatBotDashboardFragment.activity.applicationContext, TextToSpeech.OnInitListener {
            textToSpeech.language = Locale("id","ID")
        })

        return textToSpeech
    }

    fun onClick() : FloatingActionButton = fab
    fun getText() : EditText = textIsiMessage

    fun provideToast(message: String) : Toast{
        return Toast.makeText(chatBotDashboardFragment.activity, message,Toast.LENGTH_SHORT)
    }

    fun insertDataMessage(listViewType: List<Int>,listDataSms : List<SmsDataModel>){
        chatAdapter.insertDataMessage(listViewType,listDataSms)
    }





}