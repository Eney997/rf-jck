package com.example.workingretro.screens

import android.util.Log
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
import com.example.workingretro.dtos.UserDto
import com.example.workingretro.retroclientandapibridge.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun CreateUser()
{
    val name = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val context = LocalContext.current
    val corScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Column ()
        {
            TextField(value = name.value, onValueChange = {name.value = it}, placeholder = {Text("Name")})
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = lastname.value, onValueChange = {lastname.value = it},placeholder = {Text("LastName")})
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = age.value, onValueChange = {age.value = it},placeholder = {Text("Age")})
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = country.value, onValueChange = {country.value = it},placeholder = {Text("Country")})
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {

                    if(name.value.isEmpty() || lastname.value.isEmpty() || age.value.isEmpty() || country.value.isEmpty())
                    {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val myUser = UserDto(
                        userFirstName = name.value,
                        userLastName = lastname.value,
                        userAge = age.value.toInt(),
                        userCountry = country.value
                    )

                    corScope.launch {

                        try {
                            val response = RetrofitClient.apiService.createUser(myUser)

                            if (response.isSuccessful) {
                                Toast.makeText(context, "User added", Toast.LENGTH_SHORT).show()

                                // Clear fields
                                name.value = ""
                                lastname.value = ""
                                age.value = ""
                                country.value = ""
                            } else {
                                val error = response.errorBody()?.string()
                                Log.e("API_EXCEPTION", "Response code: ${response.code()}, error: $error")
                                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: Exception) {
                            Log.e("API_EXCEPTION", "Exception: ${e.localizedMessage}", e)
                            Toast.makeText(context, "Network error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            {
                Text(text = "Create")
            }
        }
    }

}