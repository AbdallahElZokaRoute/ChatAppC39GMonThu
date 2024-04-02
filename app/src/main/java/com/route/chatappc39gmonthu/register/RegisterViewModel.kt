package com.route.chatappc39gmonthu.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc39gmonthu.FirebaseUtils
import com.route.chatappc39gmonthu.model.AppUser
import com.route.chatappc39gmonthu.model.DataUtils

class RegisterViewModel : ViewModel() {
    val firstNameState = mutableStateOf("")
    val firstNameErrorState = mutableStateOf<String?>(null)
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf<String?>(null)
    val auth = Firebase.auth
    val isLoading = mutableStateOf(false)
    val event = mutableStateOf<RegisterEvent>(RegisterEvent.Idle)
    val messageState = mutableStateOf("")
    fun register() {
        if (validateFields()) {
            // Communicate with firebase
            isLoading.value = true
            auth.createUserWithEmailAndPassword(emailState.value, passwordState.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("Tag", "Error Occurred : ${task.exception?.message} ")
                        isLoading.value = false
                        return@addOnCompleteListener
                    }
                    val uid = task.result.user?.uid
                    // Save User into Firebase Cloud Firestore
                    // add Data to Fire store
                    addUserToFirestore(uid!!)
                }
        }
    }

    fun addUserToFirestore(uid: String) {
        val user = AppUser(firstNameState.value, emailState.value, uid)
        FirebaseUtils.addUser(user, onSuccessListener = {
            isLoading.value = false
            DataUtils.appUser = user
            navigateToHome(user)
        }, onFailureListener = {
            isLoading.value = false
        }
        )
    }

    fun validateFields(): Boolean {
        if (firstNameState.value.isEmpty() || firstNameState.value.isBlank()) {
            firstNameErrorState.value = "Required"
            return false
        } else
            firstNameErrorState.value = null
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
        event.value = RegisterEvent.NavigateToHome(user)
    }

}
