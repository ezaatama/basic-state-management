package com.example.basicstatemanagement

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicstatemanagement.state.LoginState
import com.example.basicstatemanagement.state.loginStateRemember
import com.example.basicstatemanagement.ui.theme.BasicStateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateManagementTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, state: LoginState = loginStateRemember(inputState = "", inputAlert = false)) {

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, modifier = modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                },
            value = state.email,
            onValueChange = { state.email = it},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            placeholder = { Text("Email") }
        )
        Spacer(modifier = modifier.height(12.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = { state.password = it},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    state.showDialog = true
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Password") }
        )
        Spacer(modifier = modifier.height(20.dp))
        Button(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), onClick = {
            state.showDialog = true
        }) {
            Text("Submit")
        }

        if(state.showDialog) {
            AlertDialog(
                onDismissRequest = {
                    state.showDialog = false
                },
                confirmButton = {
                    Button(onClick = {
                        state.showDialog = false
                    }) {
                        Text("OK")
                    }
                },
                title = {
                    Column {
                        Text("Email: ${state.email}")
                        Text("Password: ${state.password}")
                    }
                },
                text = {
                    Text("User Information")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BasicStateManagementTheme {
        LoginScreen()
    }
}