package com.example.workingretro.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.workingretro.retroclientandapibridge.RetrofitClient
import kotlinx.coroutines.launch


@Composable
fun DeleteUser()
{
    val enterId = remember { mutableStateOf("") }
    val corScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {

        Column(modifier = Modifier){
            TextField(value = enterId.value, onValueChange = {
                    val modifiedValue = it.filter { char -> char.isDigit() }
                    enterId.value = modifiedValue
                     }
                ,placeholder = {Text(text = "Name")})
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (enterId.value.isEmpty())
                    {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    corScope.launch {
                        val response = RetrofitClient.apiService.deleteUser(enterId.value.toInt())

                        if (response.isSuccessful)
                        {
                            Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(context, "User not deleted", Toast.LENGTH_SHORT).show()
                            return@launch
                        }
                    }

                }
            ) {
                Text("Click me")
            }
        }
    }
}