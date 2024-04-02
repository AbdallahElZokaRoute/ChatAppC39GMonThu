package com.route.chatappc39gmonthu.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc39gmonthu.FirebaseUtils
import com.route.chatappc39gmonthu.model.AppUser
import com.route.chatappc39gmonthu.model.DataUtils

class SplashViewModel : ViewModel() {
    val events = mutableStateOf<SplashEvent>(SplashEvent.Idle)
    val auth = Firebase.auth

    // Auto Login
    // Create a new Rooms -> Chat
    fun navigate() {
        if (auth.currentUser != null)
            auth.currentUser?.uid?.let { uid ->
                getUserFromFirestore(uid)
            } ?: navigateToLogin()
        else
            navigateToLogin()
    }

    fun getUserFromFirestore(uid: String) {
        FirebaseUtils.getUser(uid, onSuccessListener = { docSnapshot ->
            val user = docSnapshot.toObject(AppUser::class.java)
            user?.let {
                DataUtils.appUser = it
                navigateToHome(it)
            } ?: navigateToLogin()
        }, onFailureListener = {
            Log.e("TAG", "getUserFromFirestore: ${it.message}")
            navigateToLogin()
        }
        )
    }

    fun navigateToHome(user: AppUser) {
        events.value = SplashEvent.NavigateToHome(user)
    }

    fun navigateToLogin() {
        events.value = SplashEvent.NavigateToLogin
    }
}




