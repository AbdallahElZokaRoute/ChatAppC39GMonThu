package com.route.chatappc39gmonthu.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.cyan

@Composable
fun ChatAuthTextField(
    state: MutableState<String>,
    errorState: String?,
    label: String,
    isPassword: Boolean = false,
    trailingIcon: Int? = null
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = state.value,
            onValueChange = { newText ->
                state.value = newText
            },
            modifier = Modifier.fillMaxWidth(0.9F),
            label = {
                Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Normal)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = cyan,
                errorIndicatorColor = Color.Red
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (trailingIcon != null) {
                    Image(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = stringResource(
                            R.string.trailing_icon
                        )
                    )
                }
            }
        )
        if (errorState != null) {
            Text(
                text = errorState,
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 2.dp)
                    .align(Alignment.Start)
            )
        }
    }
}

