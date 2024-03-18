package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier){

    //Usually remember and mutableStateOf are used together in composable functions.
    val count : MutableState<Int> = rememberSaveable {              //To ACCESS ===> count.value
        mutableStateOf(0)
    }
//    var count by remember { mutableStateOf(0) }           //To ACCESS ===> count
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =  Modifier.fillMaxWidth()
    ){
        if(count.value in 1..9){
            val taskShow : MutableState<Boolean> = remember {
                mutableStateOf(true)
            }
            if(taskShow.value){
//                WellnessTaskItem(taskItem = "Have you taken your 15 minute walk today?", onClose = { taskShow.value = false })
            }
            Text(
                text = "You have had ${count.value} glasses",
                modifier.padding(16.dp)
            )
        }
        if(count.value == 10){
            Text(
                text = "you reach your goal for the day",
                modifier.padding(16.dp)
            )
        }

//        when click on the button, not change happen to count in the screen
        //That is because we don't tell the compose to reRender or recompose the component when state changes
        //Use Compose's State and MutableState types to make state observable by Compose, to trigger recomposition when value change
        Row(
//            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = { count.value++},
                enabled = count.value < 10,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Add one")
            }
            Button(
                onClick = { count.value = 0},
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Clear water count")
            }
        }


    }
    
}

@Preview
@Composable
fun WaterCounterPreview(){
    WaterCounter()
}


/*
stateless composable use state hoisting
State Hoisting ==> is a pattern of moving state to a composable's caller to make a composable stateless.
state hoisting in Jetpack Compose is (replace STATE variable with 2 parameters)
        => Value (T)  --> the current value to display
        => onValueChange (T) -> Unit  --> event that requests the value to change with a new value T

**Advantage of using hoisting in STATE**
1- Single source of truth ==> By moving state instead of duplicating it,
 we're ensuring there's only one source of truth. This helps avoid bugs.

2-Shareable  ==> Hoisted state can be shared with multiple composables.

3-Interceptable  => Callers to the stateless composables can decide to ignore or modify events before changing the state.

4-Decoupled  => The state for a stateless composable function can be stored anywhere. For example, in a ViewModel.
 */