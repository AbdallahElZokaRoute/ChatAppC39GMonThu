package com.route.chatappc39gmonthu.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatappc39gmonthu.Constants
import com.route.chatappc39gmonthu.login.LoginActivity
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.home.HomeActivity
import com.route.chatappc39gmonthu.ui.theme.ChatAppC39GMonThuTheme
import java.io.Serializable

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppC39GMonThuTheme {
                SplashContent {
                    finish()
                }
            }
        }
    }
}

@Composable
fun SplashContent(viewModel: SplashViewModel = viewModel(), onFinish: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.navigate()
        }, 2000)
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.application_logo_image),
            modifier = Modifier.fillMaxHeight(0.24F),
            contentScale = ContentScale.Crop
        )
//        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = stringResource(
                R.string.application_signature_image
            ),
            modifier = Modifier
                .fillMaxHeight(0.2F)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Crop
        )
    }
    TriggerEvents(event = viewModel.events.value) {
        onFinish()
    }
}

@Composable
fun TriggerEvents(
    event: SplashEvent,
    viewModel: SplashViewModel = viewModel(),
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    when (event) {
        SplashEvent.Idle -> {}
        is SplashEvent.NavigateToHome -> {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(Constants.USER_KEY, event.user)
            context.startActivity(intent)
            onFinish()
        }

        SplashEvent.NavigateToLogin -> {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            onFinish()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    SplashContent {

    }
}
