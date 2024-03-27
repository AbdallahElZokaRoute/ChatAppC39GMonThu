package com.route.chatappc39gmonthu.register

import com.route.chatappc39gmonthu.model.AppUser

sealed interface RegisterEvent {
    data object Idle : RegisterEvent
    data class NavigateToHome(val user: AppUser) : RegisterEvent

}
