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
import com.example.workingretro.dtos.UserSdTo
import com.example.workingretro.retroclientandapibridge.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun UpdateUser()
{
    val enterId = remember { mutableStateOf("") }
    val enterName = remember { mutableStateOf("") }
    val enterLastName = remember { mutableStateOf("") }
    val enterAge = remember { mutableStateOf("") }
    val enterCountry = remember { mutableStateOf("") }
    val context = LocalContext.current
    val corScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center)
    {
        Column(modifier = Modifier)
        {
            TextField(
                value = enterId.value,
                onValueChange = { enterId.value = it },
                placeholder = { Text("Search id") })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = enterName.value,
                onValueChange = { enterName.value = it },
                placeholder = { Text("Name") })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = enterLastName.value,
                onValueChange = { enterLastName.value = it },
                placeholder = { Text("LastName") })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = enterAge.value,
                onValueChange = { enterAge.value = it },
                placeholder = { Text("Age") })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = enterCountry.value,
                onValueChange = { enterCountry.value = it },
                placeholder = { Text("Country") })

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {

                if(enterId.value.isEmpty() || enterName.value.isEmpty() || enterLastName.value.isEmpty() || enterAge.value.isEmpty() || enterCountry.value.isEmpty())
                {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val myUser = UserSdTo(
                    name = enterName.value,
                    lastName = enterLastName.value,
                    age = enterAge.value.toInt(),
                    country = enterCountry.value
                )


                corScope.launch {

                    try {
                        val response = RetrofitClient.apiService.updateUser(enterId.value.toInt(), myUser)
                        if(response.isSuccessful)
                        {
                            Toast.makeText(context, "User updated", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(context, "User not updated", Toast.LENGTH_SHORT).show()
                            return@launch
                        }
                    }catch (e: Exception)
                    {
                        Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }


                }

            }
            )
            {
                Text(text = "Click me")
            }
        }
    }
}

