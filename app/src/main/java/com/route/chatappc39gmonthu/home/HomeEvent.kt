package com.route.chatappc39gmonthu.home

import com.route.chatappc39gmonthu.model.Room

sealed interface HomeEvent {
    data object Idle : HomeEvent
    data object NavigateToAddRoomScreen : HomeEvent
    data class NavigateToChatScreen(val room: Room) : HomeEvent
}