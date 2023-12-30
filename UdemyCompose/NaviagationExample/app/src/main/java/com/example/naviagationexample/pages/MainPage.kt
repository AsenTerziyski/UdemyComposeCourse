package com.example.naviagationexample.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.naviagationexample.MyButton
import com.example.naviagationexample.R
import java.lang.IllegalArgumentException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {

    var inputName by remember {
        mutableStateOf("")
    }

    var inputAge by remember {
        mutableStateOf("")
    }

    val myContext = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Main page", fontSize = 20.sp, color = Color.White)
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(id = R.color.purple_500)
            )
        )
    }, content = { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(value = inputName, onValueChange = {
                inputName = it
            }, label = {
                Text(
                    text = "Fill your name",
                    fontSize = 16.sp,
                )
            }, colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                containerColor = colorResource(id = R.color.purple_500)
            ), modifier = Modifier.size(
                width = 300.dp, height = 60.dp
            ), textStyle = TextStyle(
                fontSize = 18.sp, color = Color.White
            ), shape = RoundedCornerShape(5.dp)
            )

            Spacer(modifier = Modifier.size(30.dp))

            TextField(value = inputAge,
                onValueChange = {
                    inputAge = it
                },
                label = {
                    Text(
                        text = "Fill your age",
                        fontSize = 16.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    containerColor = colorResource(id = R.color.purple_500)
                ),
                modifier = Modifier.size(
                    width = 300.dp, height = 60.dp
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp, color = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.size(75.dp))

            MyButton(aClick = {
                if (inputName.isEmpty() || inputAge.isEmpty()) {
                    Toast.makeText(myContext, "Please fill data", Toast.LENGTH_SHORT).show()
                    return@MyButton
                }

                try {
                    val sendData = "/${inputName}/${inputAge}"
                    navController.navigate(Pages.SECOND_PAGE.aName + sendData)
//                        removes all pages from backstack including MainPage:
//                        {
//                            popUpTo(Pages.MAIN_PAIGE.aName) {inclusive = true}
//                        }
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(myContext, "Please enter numbers for the age!", Toast.LENGTH_SHORT).show()
                }

                inputName = ""
                inputAge = ""
            })
        }
    })
}

private val TOAST_MESSAGE = "Please fill your name and(or) age"