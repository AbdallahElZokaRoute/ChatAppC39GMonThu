package com.route.chatappc39gmonthu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AppUser(
    val firstName: String? = null,
    val email: String? = null,
    val uid: String? = null

) : Parcelable {
    companion object {
        val COLLECTION_NAME = "Users"

    }
}
