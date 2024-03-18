package com.example.basicstatecodelab

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WillnessTask(val id :Int , val label :String , var checked : MutableState<Boolean> = mutableStateOf(false))