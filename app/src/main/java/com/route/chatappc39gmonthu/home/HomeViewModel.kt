package com.route.chatappc39gmonthu.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val event = mutableStateOf<HomeEvent>(HomeEvent.Idle)

    fun navigateToAddRoomScreen() {
        event.value = HomeEvent.NavigateToAddRoomScreen
    }

    fun resetEventState() {
        event.value = HomeEvent.Idle
    }
}
