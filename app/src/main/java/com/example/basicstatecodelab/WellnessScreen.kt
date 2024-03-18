package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel


fun getWellnessTasks() = List(30) { i -> WillnessTask(i ,"Task #$i")}
//The extension function toMutableStateList() is the way to create an observable MutableList
// from an initial mutable or immutable Collection, such as List.
//use the factory method mutableStateListOf to create the observable MutableList
// and then add the elements for your initial state.

//The mutableStateOf function returns an object of type MutableState<T>.
//
//The mutableStateListOf and toMutableStateList functions return an object of type SnapshotStateList<T>.
// In this section, the terms "observable MutableList" refer to this class.

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel : WellnessViewModel = viewModel()
){
//    WaterCounter(modifier)
//    val checked : MutableState<Boolean> = rememberSaveable {
//        mutableStateOf(false)
//    }
//    val taskShow : MutableState<Boolean> = remember {
//        mutableStateOf(true)
//    }

//    =============================================================================================

//    WellnessTaskItem(
//        taskItem = "Task #1",
//        onClose = { taskShow.value = false},
//        check = checked.value,
//        onCheckChange = { newValue -> checked.value = newValue}
//    )


    //ImmutableList
//    val listData : List<WillnessTask> = rememberSaveable {
//        getWellnessTasks()
//    }

    //MutableList
    val listData : SnapshotStateList<WillnessTask> = remember {
        getWellnessTasks().toMutableStateList()
    }
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ){
        WaterCounter(modifier)
        Spacer(modifier = Modifier.height(8.dp))
        RenderWellnessTasksList(
            list = wellnessViewModel.tasks ,
            onCloseClicked={task -> wellnessViewModel.removeTask(task)},
            onchangeCheckState={task , checked -> wellnessViewModel.updateTaskCheck(task , checked)}
        )

    }
}

//list= listData, onCloseClicked={task -> listData.remove(task)

//as I can't persist or cache the list with rememberSaveable as previous
//so we use ViewModel class to store data list, and make functionality on it, with persisting feature,
//as ViewModel provide saving data change (caching) that prevent it to return while configuration change.