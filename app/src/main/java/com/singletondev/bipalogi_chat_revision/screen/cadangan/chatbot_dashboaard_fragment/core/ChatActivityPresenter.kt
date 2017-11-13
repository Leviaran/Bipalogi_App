package com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core

import android.graphics.BitmapFactory
import android.os.Environment
import android.speech.tts.TextToSpeech
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.model.SmsDataModel
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.ChatAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.alicebot.ab.*
import java.io.*
import java.util.*
import java.util.regex.Pattern
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonAdapter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.ButtonClickListener
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.list.button_adapter.hookClickListenerFromButton
import com.singletondev.bipalogi_chat_revision.utils.SharedPref
import kotlin.collections.ArrayList


/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatActivityPresenter (
        var chatActivityView: ChatActivityView,
        var chatActivityModel: ChatActivityModel,
        var fileDir : File,
        var compositeDisposable: CompositeDisposable
) : ButtonClickListener {

    lateinit var bot: Bot
    lateinit var chat: Chat
    lateinit var listViewType: MutableList<Int>
    lateinit var listDataSms: MutableList<SmsDataModel>
    lateinit var textToSpeech : TextToSpeech
    var listString = ArrayList<String>()
    var buttonAdapter = ButtonAdapter()

    private val dataSms : MutableList<SmsDataModel> by lazy {
        ArrayList<SmsDataModel>()
    }

//    fun insertButtonString(listString : MutableList<String>){
//        this.listString = listString
//    }

    fun onCreate(){
        compositeDisposable.add(loadDataObservable())
        onInitChatAdapter()
    }

    fun onInitChatAdapter(){
        //init navigation
//      chatAdapter.buttonAdapter.updateStringButtonList(listString)
        chatActivityView.containerRecyclerViewButton.layoutManager = LinearLayoutManager(chatActivityView.provideContext(), LinearLayoutManager.HORIZONTAL,false)
        buttonAdapter.setClickListener(this)
        chatActivityView.containerRecyclerViewButton.adapter = buttonAdapter
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }

    private fun loadDataObservable(): Disposable {
        addToListData()
        return Observable.fromCallable { loadData() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnComplete {
                    //Provide SpeechMessage Bot
                    textToSpeech = chatActivityView.provideTextToSpeech()
                    readyForClick()
                }
                .subscribe()
    }

    private fun loadData() {
        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/chatbot"
        AIMLProcessor.extension = PCAIMLProcessorExtension()

        bot = Bot("Rand",MagicStrings.root_path,"chat")
        chat = Chat(bot)

    }

    private fun addToListData(){
        listViewType = ArrayList()
        listDataSms = ArrayList()
        var dataSms = SmsDataModel("Silakan pilih jawaban berikut, bila ada kata yang tidak jelas tanyakan saja saya")
        listDataSms.add(dataSms)
        listViewType.add(ChatAdapter.TYPE_KIRI)
        chatActivityView.insertDataMessage(listViewType,listDataSms)
    }

    override fun onItemClick(view: View, position: Int) {
        listViewType.add(ChatAdapter.TYPE_KANAN)
        var dataSms = SmsDataModel(listString[position])
        listDataSms.add(dataSms)
        botRespons(listString[position])
    }

    fun hookIntoMessage(messageHook : String){
        addToListData()
        textToSpeech = chatActivityView.provideTextToSpeech()
        when {
            !TextUtils.isEmpty(messageHook) -> {
                Observable.fromCallable { loadData() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnComplete {
                            //Provide SpeechMessage Bot
                            listViewType.add(ChatAdapter.TYPE_KANAN)
                            var dataSms = SmsDataModel(messageHook)
                            listDataSms.add(dataSms)
                            botRespons(messageHook)
                        }
                        .subscribe()
            }
        }
    }

    private fun readyForClick(){
        var pesanIsiText: EditText = chatActivityView.getText()
        chatActivityView.onClick().setOnClickListener {
            var pesan : String = pesanIsiText.text.toString().trim()
            when {
                TextUtils.isEmpty(pesan) -> {
                    chatActivityView.provideToast("Masukkan Pesan Anda terlebih dulu").show()
                    pesanIsiText.setError("Pesan Kosong")
                }

                else -> {
                    pesanIsiText.text = null
                    listViewType.add(ChatAdapter.TYPE_KANAN)
                    var dataSms = SmsDataModel(pesan)
                    listDataSms.add(dataSms)
                    botRespons(pesan)

                }
            }
        }
    }

    private fun botRespons(message : String){
        var respons: String = chat.multisentenceRespond(message)
        if (respons.trim().toLowerCase().equals("i have no answer for that.")) respons = "Saya tidak mengerti maksud Anda"
        else SharedPref.saveNilaiInt(SharedPref.SCOREVOCAB,SharedPref.getInt(SharedPref.SCOREVOCAB)+1)
        removeOobtag(respons)
        chatActivityView.refreshDataList()
//        var dataSmsFromBot = SmsDataModel(respons)
//        listViewType.add(ChatAdapter.TYPE_KIRI)
//        listDataSms.add(dataSmsFromBot)
//        chatActivityView.refreshDataList()
//        textToSpeech.speak(respons,TextToSpeech.QUEUE_FLUSH,null)
    }

    fun removeOobtag(output : String?){
        when {
            output != null -> {
                var pattern = Pattern.compile("<oob>(.*)</oob>",Pattern.DOTALL)
                var matcher = pattern.matcher(output)
                if (matcher.find()){
                    var oobContent = matcher.group(1)
//                    processInnerOobTag(oobContent)
                    var respons = matcher.replaceAll("")
                    Log.e("respon",respons + " dan " + oobContent)
                    var dataSmsFromBot = SmsDataModel(respons.trim())
                    listViewType.add(ChatAdapter.TYPE_KIRI)
                    listDataSms.add(dataSmsFromBot)
                    textToSpeech.speak(respons,TextToSpeech.QUEUE_FLUSH,null)
                    processInnerOobTag(oobContent)
                } else {
                    listString.clear()
                    var dataSmsFromBot = SmsDataModel(output.trim())
                    var listString = ArrayList<String>()
                    listString.add("null")
                    listViewType.add(ChatAdapter.TYPE_KIRI)
                    listDataSms.add(dataSmsFromBot)
                    textToSpeech.speak(output,TextToSpeech.QUEUE_FLUSH,null)
                    buttonAdapter.updateStringButtonList(listString)
                }
            }
        }
    }

    fun processInnerOobTag(oobContent: String) {
        listString.clear()
        when {
            oobContent.contains("<dialog>") -> {
                Log.e("Checking","yes")
                var pattern = Pattern.compile("<dialog>(.*)</dialog>")
                var matcher = pattern.matcher(oobContent)
                Log.e("matcher",oobContent)
                if (matcher.find()){
                    var content = matcher.group(1)
                    var respons = matcher.replaceAll("")
                    Log.e("respon",respons + " dan " + oobContent)
                    var dataSmsFromBot = SmsDataModel(respons.trim())
                    listViewType.add(ChatAdapter.TYPE_KIRI)
                    listDataSms.add(dataSmsFromBot)
                    textToSpeech.speak(respons,TextToSpeech.QUEUE_FLUSH,null)
                    processInnerOobTag(content)
                }
            }

            oobContent.contains("<dial>") -> {
                Log.e("berhasilkan","berhasilkah??")
                var pattern = Pattern.compile("<dial>(.*)</dial>")
                var matcher = pattern.matcher(oobContent)
                if (matcher.find()){
                    var number = matcher.group(1)
                    Toast.makeText(chatActivityView.provideContext(),"Dial to ${number}",Toast.LENGTH_SHORT).show()
                }
            }

            oobContent.contains("Ya , Tidak") -> {
                Log.e("berhasilkan","berhasilkah ya tidak")
                var stringTokenizer = StringTokenizer(oobContent, ",")
                while (stringTokenizer.hasMoreElements()){
                    listString.add(stringTokenizer.nextElement().toString())
                }
                buttonAdapter.updateStringButtonList(listString)
//                chatActivityView.provideChatAdapter().insertButtonString(listString)
            }

            oobContent.contains("Malaysia , Thailand") -> {
                Log.e("berhasilkan","berhasilkah malay")
                var stringTokenizer = StringTokenizer(oobContent, ",")
                while (stringTokenizer.hasMoreElements()){
                    listString.add(stringTokenizer.nextElement().toString())
                }
                buttonAdapter.updateStringButtonList(listString)
//                chatActivityView.provideChatAdapter().insertButtonString(listString)
            }

            oobContent.contains("<nilai>") -> {
                SharedPref.saveNilaiInt(SharedPref.SCOREPERCOBAAN,SharedPref.getInt(SharedPref.SCOREPERCOBAAN)+1)
            }

            oobContent.contains("Terima kasih") -> {
                var stringTokenizer = StringTokenizer(oobContent, ",")
                while (stringTokenizer.hasMoreElements()){
                    listString.add(stringTokenizer.nextElement().toString())
                }
                buttonAdapter.updateStringButtonList(listString)
//                chatActivityView.provideChatAdapter().insertButtonString(listString)
            } else -> {

        }

        }
    }

    fun setupImageRounded(imageView: ImageView){
        var bitmap = BitmapFactory.decodeResource(chatActivityView.provideContext().resources,R.drawable.bot_master1)
        var roundBitmapDrawable = RoundedBitmapDrawableFactory.create(chatActivityView.provideContext().resources,bitmap)
        roundBitmapDrawable.cornerRadius = bitmap.width.toFloat()

        imageView.setImageDrawable(roundBitmapDrawable)
    }
}