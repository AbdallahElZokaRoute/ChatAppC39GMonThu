package com.route.chatappc39gmonthu.home

import android.content.Intent
import android.hardware.TriggerEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.ChatAppC39GMonThuTheme
import com.route.chatappc39gmonthu.ui.theme.cyan
import com.route.chatappc39gmonthu.utils.ChatTopBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.Constants
import com.route.chatappc39gmonthu.addRoom.AddRoomActivity
import com.route.chatappc39gmonthu.chat.ChatActivity
import com.route.chatappc39gmonthu.model.Category
import com.route.chatappc39gmonthu.model.Room

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
                .padding(top = paddingValues.calculateTopPadding())
                .padding(top = 32.dp)
        ) {
            // Lazy Vertical Grid
            RoomsLazyGrid()
        }
    }
    TriggerEvent(event = viewModel.event.value)
}

@Composable
fun RoomsLazyGrid(viewModel: HomeViewModel = viewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getRoomsFromFirestore()
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(viewModel.roomsList.size) { position ->
            // RoomCard
            RoomCard(room = viewModel.roomsList[position])
        }
    }
}

@Composable
fun RoomCard(room: Room, viewModel: HomeViewModel = viewModel()) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        onClick = {
            viewModel.navigateToChatScreen(room)
        }
    ) {
        Image(
            painter = painterResource(
                id = Category.fromId(room.categoryId ?: Category.MOVIES).image ?: R.drawable.movies
            ), contentDescription = stringResource(R.string.room_category_image),
            modifier = Modifier
                .size(86.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Text(
            text = room.name ?: "", fontSize = 14.sp, fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
private fun RoomPreview() {
    RoomCard(room = Room("Android Room", Category.SPORTS))
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

        is HomeEvent.NavigateToChatScreen -> {
            val intent = Intent(context, ChatActivity::class.java)
            // Parcelable
            intent.putExtra(Constants.ROOM_KEY, event.room)
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