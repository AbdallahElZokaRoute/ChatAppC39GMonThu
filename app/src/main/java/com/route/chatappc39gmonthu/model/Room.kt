package com.route.chatappc39gmonthu.model

data class Room(
    val name: String? = null,
    val categoryId: String? = null,
    val description: String? = null,
) {
    companion object {
        val COLLECTION_NAME = "Rooms"
    }
}

