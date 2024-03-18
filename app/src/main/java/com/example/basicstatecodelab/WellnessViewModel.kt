package com.example.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel :ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()

    //get instance of the list to be used outside of the class
    val tasks : List<WillnessTask> get() = _tasks

    fun removeTask ( task: WillnessTask){
        _tasks.remove(task)
    }

    fun updateTaskCheck(task: WillnessTask , checkState :Boolean){
        _tasks.find { it.id == task.id }?.let {
            it.checked.value = checkState
        }
    }
}


//ViewModel    ==> ADVANTAGE

//caches state and persists it through configuration changes (as rotate phone screen).
//so it prevent from fetching data again when navigating between activities,
// or following configuration changes, such as when rotating the screen.
//ViewModels are not part of the Composition