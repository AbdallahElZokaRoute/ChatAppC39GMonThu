package com.route.chatappc39gmonthu.login

import com.route.chatappc39gmonthu.model.AppUser

sealed interface LoginEvent {
    data object Idle : LoginEvent
    data object NavigateToRegistration : LoginEvent
    data class NavigateToHome(val user: AppUser) : LoginEvent
}