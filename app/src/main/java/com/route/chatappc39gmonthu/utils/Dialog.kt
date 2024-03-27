package com.route.chatappc39gmonthu.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.cyan
import com.route.chatappc39gmonthu.ui.theme.white

@Composable
fun LoadingDialog(isLoading: MutableState<Boolean>) {
    if (isLoading.value) {
        Dialog(onDismissRequest = { isLoading.value = false }) {
            CircularProgressIndicator(
                color = cyan, modifier = Modifier
                    .background(
                        white,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(36.dp)
            )
        }
    }
}

@Composable
fun ErrorDialog(title: MutableState<String>) {
    if (title.value.isNotEmpty()) {
        AlertDialog(onDismissRequest = {
            title.value = ""
        }, confirmButton = {
            TextButton(onClick = { title.value = "" }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
            text = {
                Text(text = title.value)
            }
        )
    }
}
