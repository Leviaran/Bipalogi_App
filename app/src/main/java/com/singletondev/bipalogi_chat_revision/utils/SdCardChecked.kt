package com.singletondev.bipalogi_chat_revision.utils

import android.content.Context
import android.os.Environment
import io.reactivex.Observable

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class SdCardChecked {
    companion object {
        fun isSdCardAvalaible(context: Context) : Boolean =
                Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

        fun isSdCardAvalaibleObservable(context: Context): Observable<Boolean> =
                Observable.just(SdCardChecked.Companion.isSdCardAvalaible(context))
    }
}