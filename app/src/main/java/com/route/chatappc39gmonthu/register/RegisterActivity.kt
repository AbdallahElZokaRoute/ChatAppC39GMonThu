package com.route.chatappc39gmonthu.register

import android.content.Intent
import android.hardware.TriggerEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.route.chatappc39gmonthu.utils.ChatAuthTextField
import com.route.chatappc39gmonthu.utils.ChatTopBar
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.home.HomeActivity
import com.route.chatappc39gmonthu.utils.ChatAuthButton
import com.route.chatappc39gmonthu.utils.ErrorDialog
import com.route.chatappc39gmonthu.utils.LoadingDialog

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppC39GMonThuTheme {
                RegisterContent(onRegisterSuccess = {
                    finishAffinity()
                }, onFinish = {
                    finish()
                })
            }
        }
    }
}

@Composable
fun RegisterContent(
    viewModel: RegisterViewModel = viewModel(),
    onFinish: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    Scaffold(
        topBar = {
            ChatTopBar(title = stringResource(id = R.string.register)) {
                onFinish()
            }
        }
    ) { paddingValues ->
        paddingValues
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.4F))
            ChatAuthTextField(
                state = viewModel.firstNameState,
                errorState = viewModel.firstNameErrorState.value,
                label = stringResource(
                    id = R.string.first_name
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ChatAuthTextField(
                state = viewModel.emailState,
                errorState = viewModel.emailErrorState.value,
                label = stringResource(
                    id = R.string.email
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ChatAuthTextField(
                state = viewModel.passwordState,
                errorState = viewModel.passwordErrorState.value,
                label = stringResource(
                    id = R.string.password
                ),
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1F))
            ChatAuthButton(
                title = stringResource(id = R.string.register),
                onClickListener = {
                    viewModel.register()
                },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(32.dp))


        }
        TriggerEvent(event = viewModel.event.value) {
            onRegisterSuccess()
        }
        LoadingDialog(isLoading = viewModel.isLoading)
        ErrorDialog(title = viewModel.messageState)
    }
}

@Composable
fun TriggerEvent(
    event: RegisterEvent,
    viewModel: RegisterViewModel = viewModel(),
    onSuccessRegister: () -> Unit
) {
    val context = LocalContext.current
    when (event) {
        RegisterEvent.Idle -> {}
        is RegisterEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            onSuccessRegister()
        }
    }
}

@Preview
@Composable
private fun RegisterPreview() {
    RegisterContent(onFinish = {}) {}
}
