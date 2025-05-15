package com.example.workingretro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.workingretro.screens.CreateUser
import com.example.workingretro.screens.DeleteUser
import com.example.workingretro.screens.ReadDataScreen
import com.example.workingretro.screens.UpdateUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //create user
            //CreateUser()

            //Read data from database
            //ReadDataScreen()

            //delete user
            //DeleteUser()

            //update user
            UpdateUser()


        }
    }
}