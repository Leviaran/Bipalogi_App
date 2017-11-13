package com.singletondev.bipalogi_chat_revision.utils

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class CopyFile {
    companion object {
        val compositeDisposable : CompositeDisposable by lazy {
            CompositeDisposable()
        }
        fun copyFile(input: InputStream, output: OutputStream){
            val bufferreader = ByteArray(512)
            var baca : Int = 0

            while ({baca = input.read(bufferreader); baca}() > 0) {
                output.write(bufferreader, 0, baca)
                Log.e("working","Working")
            }
        }
        fun CopyFileProcess(input: InputStream,output: OutputStream){
            var observable : Disposable = Observable.just(copyFile(input, output))
                    .subscribeOn(Schedulers.io())
                    .doOnError { Log.e("Error Copying","Occure") }
                    .subscribe()

            compositeDisposable.add(observable)
        }

        fun UnDispose(){
            compositeDisposable.clear()
        }


    }
}