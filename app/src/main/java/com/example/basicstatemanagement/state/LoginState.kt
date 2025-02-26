package com.example.basicstatemanagement.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class LoginState(initInput: String, initAlert: Boolean) {
    var email by mutableStateOf(initInput)

    var password by mutableStateOf(initInput)

    var showDialog by mutableStateOf(initAlert)
}

@Composable
fun loginStateRemember(inputState: String, inputAlert: Boolean): LoginState = remember {
    LoginState(inputState, inputAlert)
}