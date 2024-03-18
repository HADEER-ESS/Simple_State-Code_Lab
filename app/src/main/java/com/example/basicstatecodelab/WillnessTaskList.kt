package com.example.basicstatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList




@Composable
fun RenderWellnessTasksList(
    list : List<WillnessTask>,
    onCloseClicked : (WillnessTask) -> Unit,
    onchangeCheckState : (WillnessTask , Boolean) -> Unit
){
    LazyColumn {
        items(
            items = list,
            key = { task -> task.id}
        ){
            task -> WellnessTaskItem(
                taskItem = task.label,
                onClose = { onCloseClicked(task)},
                check = task.checked.value,
                onCheckChange = {checked -> onchangeCheckState(task , checked)}
            )
        }
    }
}

//            onClose = { taskShow.value = false},
//            check = checked.value,
//            onCheckChange = {newValue -> checked.value = newValue}