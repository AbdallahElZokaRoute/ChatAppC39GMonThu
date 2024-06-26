package com.route.chatappc39gmonthu.addRoom

import android.hardware.TriggerEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.ChatAppC39GMonThuTheme
import com.route.chatappc39gmonthu.ui.theme.black3
import com.route.chatappc39gmonthu.utils.ChatAuthTextField
import com.route.chatappc39gmonthu.utils.ChatTopBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.utils.CreateButton
import com.route.chatappc39gmonthu.utils.LoadingDialog

class AddRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppC39GMonThuTheme {
                AddRoomContent {
                    finish()
                }
            }
        }
    }
}

@Composable
fun AddRoomContent(
    viewModel: AddRoomViewModel = viewModel(),
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = {
            ChatTopBar(title = stringResource(id = R.string.chat_app)) {
                onFinish()
            }
        }
    ) { paddingValues ->
        paddingValues
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Card(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.9F)
                    .align(Alignment.CenterHorizontally),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    text = stringResource(R.string.create_new_room),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = black3,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_group_image),
                    contentDescription = stringResource(R.string.add_room_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight(0.1F)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                ChatAuthTextField(
                    state = viewModel.roomNameState,
                    errorState = viewModel.roomNameErrorState.value,
                    label = stringResource(R.string.room_name)
                )
                Spacer(modifier = Modifier.height(12.dp))
                CategoryDropDownMenu(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(12.dp))
                ChatAuthTextField(
                    state = viewModel.roomDescriptionState,
                    errorState = viewModel.roomDescriptionErrorState.value,
                    label = stringResource(R.string.room_desc)
                )
                Spacer(modifier = Modifier.height(12.dp))
                CreateButton(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    viewModel.addRoom()
                }
            }

        }
    }
    TriggerEvent(event = viewModel.events.value) {
        onFinish()
    }
    LoadingDialog(isLoading = viewModel.isLoading)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropDownMenu(modifier: Modifier = Modifier, viewModel: AddRoomViewModel = viewModel()) {
    ExposedDropdownMenuBox(expanded = viewModel.isCategoryExpanded.value, onExpandedChange = {
        viewModel.isCategoryExpanded.value = !viewModel.isCategoryExpanded.value
    }, modifier = modifier) {
        // Outlined Text Field
        OutlinedTextField(
            value = viewModel.selectedCategoryItem.value.name ?: "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isCategoryExpanded.value)
            },
            leadingIcon = {
                Image(
                    painter = painterResource(
                        id = viewModel.selectedCategoryItem.value.image ?: R.drawable.sports,
                    ),
                    modifier = Modifier.size(60.dp),

                    contentDescription = stringResource(R.string.selected_category_image),
                )
            },
            modifier = Modifier.menuAnchor()
        )
        // Exposed Drop Down Menu
        ExposedDropdownMenu(
            expanded = viewModel.isCategoryExpanded.value,
            onDismissRequest = { viewModel.isCategoryExpanded.value = false }) {
            viewModel.categoriesList.forEach { category ->
                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = category.image ?: R.drawable.sports),
                            contentDescription = stringResource(R.string.category_image),
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = category.name ?: "")
                    }

                }, onClick = {
                    viewModel.selectedCategoryItem.value = category
                    viewModel.isCategoryExpanded.value = false
                })
            }
        }

    }

}

@Composable
fun TriggerEvent(
    event: AddRoomEvent,
    viewModel: AddRoomViewModel = viewModel(), onFinish: () -> Unit
) {
    when (event) {
        AddRoomEvent.Idle -> {}
        AddRoomEvent.NavigateBack -> {
            onFinish()

        }
    }
}

@Preview
@Composable
private fun AddRoomPreview() {
    AddRoomContent {

    }
}
