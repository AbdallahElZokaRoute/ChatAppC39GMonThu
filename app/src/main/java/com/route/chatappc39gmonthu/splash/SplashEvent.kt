package com.route.chatappc39gmonthu.splash

import com.route.chatappc39gmonthu.model.AppUser

sealed interface SplashEvent {
    data object Idle : SplashEvent
    data class NavigateToHome(val user: AppUser) : SplashEvent
    data object NavigateToLogin : SplashEvent
}