package com.route.chatappc39gmonthu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val name: String? = null,
    val categoryId: String? = null,
    val description: String? = null,
    var id: String? = null
) : Parcelable {
    companion object {
        val COLLECTION_NAME = "Rooms"
    }
}

