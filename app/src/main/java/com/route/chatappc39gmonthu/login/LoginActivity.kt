package com.route.chatappc39gmonthu.login

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
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
import com.route.chatappc39gmonthu.ui.theme.black
import com.route.chatappc39gmonthu.utils.ChatAuthTextField
import com.route.chatappc39gmonthu.utils.ChatTopBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.home.HomeActivity
import com.route.chatappc39gmonthu.register.RegisterActivity
import com.route.chatappc39gmonthu.ui.theme.black2
import com.route.chatappc39gmonthu.utils.ChatAuthButton
import com.route.chatappc39gmonthu.utils.ErrorDialog
import com.route.chatappc39gmonthu.utils.LoadingDialog

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppC39GMonThuTheme {
                LoginContent {
                    finish()
                }
            }
        }
    }
}

@Composable
fun LoginContent(viewModel: LoginViewModel = viewModel(), onFinish: () -> Unit) {
    Scaffold(topBar = {
        ChatTopBar(stringResource(R.string.login))
    }, modifier = Modifier.fillMaxSize()) { paddingValues ->
        paddingValues
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.35F))
            Text(
                text = stringResource(R.string.welcome_back),
                color = black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            ChatAuthTextField(
                state = viewModel.emailState, viewModel.emailErrorState.value,
                stringResource(id = R.string.email),
                trailingIcon = R.drawable.ic_check_mark
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ChatAuthTextField(
                state = viewModel.passwordState, viewModel.passwordErrorState.value,
                stringResource(id = R.string.password),
                isPassword = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            ChatAuthButton(
                title = stringResource(id = R.string.login),
                onClickListener = {
                    viewModel.login()
                },
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            TextButton(onClick = {
                viewModel.navigateToRegister()
            }, modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(R.string.or_create_an_account),
                    color = black2,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
        }
    }
    TriggerEvent(event = viewModel.event.value) {
        onFinish()
    }
    LoadingDialog(isLoading = viewModel.isLoading)
    ErrorDialog(title = viewModel.messageState)
}

@Composable
fun TriggerEvent(event: LoginEvent, viewModel: LoginViewModel = viewModel(), onFinish: () -> Unit) {
    val context = LocalContext.current
    when (event) {
        LoginEvent.Idle -> {}
        is LoginEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            onFinish()

        }

        LoginEvent.NavigateToRegistration -> {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
            viewModel.resetEventState()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginPreview() {
    LoginContent {

    }
}
