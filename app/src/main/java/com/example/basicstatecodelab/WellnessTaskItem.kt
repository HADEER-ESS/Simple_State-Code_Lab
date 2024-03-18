package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

//recieve paramter to be displayed as text string
@Composable
fun WellnessTaskItem(
    taskItem : WillnessTask,
    onCloseTask: () -> Unit,
    modifier: Modifier = Modifier,
    wellnessViewModel : WellnessViewModel = viewModel()
){
    val checked : MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

//    val taskClose : MutableState<Boolean> = remember {
//        mutableStateOf(true)
//    }

    WellnessTaskItem(
        taskItem = taskItem.label,
        onClose = onCloseTask,
        check = checked.value,
        onCheckChange = { newVal -> wellnessViewModel.updateTaskCheck(taskItem , newVal)},
        modifier = Modifier
    )

}

@Composable
fun WellnessTaskItem(
    taskItem : String,
    onClose : () -> Unit,
    check : Boolean,
    onCheckChange : (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            Text(
                text = taskItem,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = check ,
                onCheckedChange =  onCheckChange
            )
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close icon")
            }
        }
}


