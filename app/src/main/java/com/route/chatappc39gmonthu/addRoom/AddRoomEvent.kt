package com.route.chatappc39gmonthu.addRoom

sealed interface AddRoomEvent {
    data object Idle : AddRoomEvent
    data object NavigateBack : AddRoomEvent
}