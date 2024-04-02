package com.route.chatappc39gmonthu.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatappc39gmonthu.R
import com.route.chatappc39gmonthu.ui.theme.black
import com.route.chatappc39gmonthu.ui.theme.black2
import com.route.chatappc39gmonthu.ui.theme.cyan
import com.route.chatappc39gmonthu.ui.theme.gray
import com.route.chatappc39gmonthu.ui.theme.white

@Composable
fun ChatAuthButton(
    title: String, onClickListener: () -> Unit, enabled: Boolean, modifier: Modifier = Modifier
) {
    // Token <->    XML    <-> Compose  ->  1.5 Month  )
    // MVI - Coroutines  20 % -  30 %  <-> Clean Architecture  <-> XML or Compose
    Button(
        modifier = if (enabled) modifier else modifier.shadow(
            spotColor = black,
            shape = RoundedCornerShape(6.dp),
            ambientColor = black,
            elevation = 5.dp,
        ),
        onClick = { onClickListener() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) cyan else Color.White,
            contentColor = if (enabled) white else gray
        ),
        shape = RoundedCornerShape(6.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = if (enabled) R.drawable.ic_forward else R.drawable.ic_forward_black),
            contentDescription = stringResource(R.string.icon_forward),
        )
    }
}

@Composable
fun CreateButton(modifier: Modifier = Modifier, onClickListener: () -> Unit) {
    Button(
        modifier = modifier.fillMaxWidth(0.8F),
        onClick = { onClickListener() },
        colors = ButtonDefaults.buttonColors(containerColor = cyan, contentColor = Color.White),
        shape = CircleShape
    ) {
        Text(
            text = stringResource(R.string.create),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SendButton(onClickListener: () -> Unit) {
    Button(
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = cyan, contentColor = Color.White),
        onClick = { onClickListener() }) {
        Text(text = stringResource(R.string.send))
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_send), contentDescription = stringResource(
                R.string.icon_send_a_message
            )
        )
    }
}

@Preview
@Composable
private fun SendPreview() {
    SendButton {

    }
}


/**   OOP & Design Patterns
 *        SOLID design Principles
 *
 *
 *      Linkedin  <-> Islami ->
 *
 *      English ->
 *      HR -> Arabic
 *      Technical Interview -> English
 *
 *      Outro Message <->  Linkedin    Github <->
 *
 *      MVI
 *      Coroutines
 *      Clean Architecture
 *      Unit Testing  <->   Code that "tests" another Code
 *      UI Testing <-> Espresso UI Testing
 *    Development                                      Tester  <-> Bug Report
 *    // Scenario  ->
 *     Assignment -> 70%  -> Certified         APIs
 *     FrameLayout  ->   add    replace  <->   SQL Server (DB_USER="host",DB_PASSWORD="root", SQLInjection)
my SQL
APIs and Retrofit
MVVM UI architecture


 */
