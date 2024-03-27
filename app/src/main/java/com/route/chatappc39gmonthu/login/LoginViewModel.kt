package com.route.chatappc39gmonthu.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc39gmonthu.FirebaseUtils
import com.route.chatappc39gmonthu.model.AppUser

class LoginViewModel : ViewModel() {
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)

    // dagger hilt
    // by viewModels()    ->   <->  ViewModelProvider(this).get(HomeViewModel::class.java)
    // live data + XML            -      Jetpack compose + Compose States
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf<String?>(null)
    val event = mutableStateOf<LoginEvent>(LoginEvent.Idle) // LoginEvent.NavigateToRegister
    val auth = Firebase.auth
    val isLoading = mutableStateOf(false)
    val messageState = mutableStateOf("")

    //
    fun login() {
        if (validateFields()) {
            // Communicate with firebase
            isLoading.value = true
            auth.signInWithEmailAndPassword(emailState.value, passwordState.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("Tag", "Error Occurred : ${task.exception?.message} ")
                        isLoading.value = false
                        messageState.value = task.exception?.message ?: "Error Occurred"
                        return@addOnCompleteListener
                    }
                    val uid = task.result.user?.uid
                    // Save User into Firebase Cloud Firestore
                    // add Data to Fire store
                    getUserFromFirestore(uid!!)
                }
        }
    }

    fun getUserFromFirestore(uid: String) {
        FirebaseUtils.getUser(uid, onSuccessListener = { docSnapshot ->
            isLoading.value = false
            val user = docSnapshot.toObject(AppUser::class.java)
            navigateToHome(user!!)
        }, onFailureListener = {
            isLoading.value = false
            messageState.value = it.message ?: ""
            Log.e("TAG", "getUserFromFirestore: ${it.message}")
        }
        )
    }

    fun validateFields(): Boolean {
        if (emailState.value.isEmpty() || emailState.value.isBlank()) {
            emailErrorState.value = "Required"
            return false
        } else
            emailErrorState.value = null
        if (passwordState.value.isEmpty() || passwordState.value.isBlank()) {
            passwordErrorState.value = "Required"
            return false
        } else
            passwordErrorState.value = null
        if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {
            emailErrorState.value = "Invalid Email Address"
            return false
        } else
            emailErrorState.value = null
        if (passwordState.value.length < 6) {
            passwordErrorState.value = "Password can't be less that 6 chars"
            return false
        } else
            passwordErrorState.value = null

        return true
    }

    fun navigateToHome(user: AppUser) {
        event.value = LoginEvent.NavigateToHome(user)
    }

    fun navigateToRegister() {
        event.value = LoginEvent.NavigateToRegistration
    }

    fun resetEventState() {
        event.value = LoginEvent.Idle
    }

}