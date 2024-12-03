package com.example.roombooking.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roombooking.R

@Composable
fun LoginWithButtonAndImage(modifier: Modifier = Modifier, onLoginClicked: () -> Unit, onRegisterClicked: () -> Unit)
{
    var logRegStatus by remember { mutableStateOf(1) }
    val imageResource = when (logRegStatus) {
        1 -> R.drawable.studyroom
        2 -> R.drawable.welcome
        else -> R.drawable.nice_to_meet_you
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(imageResource), contentDescription = logRegStatus.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            logRegStatus = 2
            onLoginClicked() // Navigate to HomeScreen
        }) {
            Text(stringResource(R.string.login))
        }
        Button(onClick = {
            logRegStatus = 3
            onRegisterClicked() // Navigate to Register screen
        }) {
            Text(stringResource(R.string.register))
        }
    }
}