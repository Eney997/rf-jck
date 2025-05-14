package com.example.workingretro.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workingretro.dtos.UserSdTo
import com.example.workingretro.viewmodel.UserViewModel


@Composable
fun ReadDataScreen() {
    val usrViewModel: UserViewModel = viewModel()
    val users by usrViewModel.userVm.collectAsState()

    LaunchedEffect(Unit) {
        usrViewModel.fetchUsers()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (users.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { userSdTo ->
                    UserCard(userSdTo)
                }
            }
        } else {
            // Display a message if the list is empty
            Text(
                text = "No users available",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



@Composable
fun UserCard(userSdTo: UserSdTo) {
    Box(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 50.dp, bottom = 50.dp)) {
        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
            Text(text = "Name: ${userSdTo.name}")
            Text(text = "Last Name: ${userSdTo.lastName}")
            Text(text = "Age: ${userSdTo.age}")
            Text(text = "Country: ${userSdTo.country}")
        }
    }
}