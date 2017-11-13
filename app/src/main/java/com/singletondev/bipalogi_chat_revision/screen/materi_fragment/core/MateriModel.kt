package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.core

import com.singletondev.bipalogi_chat_revision.model.MateriDataModel

/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class MateriModel(var materiDataModel: MateriDataModel) {

    fun provideMateriData() : MateriDataModel = materiDataModel

}