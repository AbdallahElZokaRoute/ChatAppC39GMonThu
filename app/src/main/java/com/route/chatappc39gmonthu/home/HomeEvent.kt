package com.route.chatappc39gmonthu.home

sealed interface HomeEvent {
    data object Idle : HomeEvent
    data object NavigateToAddRoomScreen : HomeEvent

}