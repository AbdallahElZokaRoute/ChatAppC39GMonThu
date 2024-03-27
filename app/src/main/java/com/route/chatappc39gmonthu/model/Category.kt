package com.route.chatappc39gmonthu.model

import com.route.chatappc39gmonthu.R

data class Category(
    val id: String? = null,
    val name: String? = null,
    val image: Int? = null
) {
    companion object {
        val MUSIC = "MUSIC"
        val MOVIES = "MOVIES"
        val SPORTS = "SPORTS"

        fun getCategoriesList(): List<Category> {
            return listOf(
                fromId(MUSIC),
                fromId(MOVIES),
                fromId(SPORTS)
            )
        }

        fun fromId(id: String): Category {
            return when (id) {
                MUSIC -> Category(MUSIC, "Music", R.drawable.music)
                MOVIES -> Category(MOVIES, "Movies", R.drawable.movies)
                SPORTS -> Category(SPORTS, "Sports", R.drawable.sports)
                else -> Category(SPORTS, "Sports", R.drawable.sports)
            }
        }
    }


}
