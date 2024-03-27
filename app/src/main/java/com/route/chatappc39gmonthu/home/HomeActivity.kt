package com.route.chatappc39gmonthu.home

import android.content.Intent
import android.hardware.TriggerEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.ChatAppC39GMonThuTheme
import com.route.chatappc39gmonthu.ui.theme.cyan
import com.route.chatappc39gmonthu.utils.ChatTopBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.addRoom.AddRoomActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppC39GMonThuTheme {
                HomeContent()
            }
        }
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel = viewModel()) {
    Scaffold(
        topBar = {
            ChatTopBar(title = stringResource(id = R.string.chat_app))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.navigateToAddRoomScreen()
                },
                shape = CircleShape,
                containerColor = cyan,
                modifier = Modifier.size(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(
                        R.string.icon_add_room
                    ),
                )

            }
        }
    ) { paddingValues ->
        paddingValues
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds)
        ) {

        }
    }
    TriggerEvent(event = viewModel.event.value)
}

@Composable
fun TriggerEvent(
    event: HomeEvent,
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    when (event) {
        HomeEvent.Idle -> {}

        HomeEvent.NavigateToAddRoomScreen -> {
            val intent = Intent(context, AddRoomActivity::class.java)
            context.startActivity(intent)
            viewModel.resetEventState()
        }

    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeContent()
}