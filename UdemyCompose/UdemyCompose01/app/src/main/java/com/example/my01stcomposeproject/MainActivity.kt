package com.example.my01stcomposeproject

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my01stcomposeproject.ui.theme.My01stComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My01stComposeProjectTheme {
                myComponents()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myComponents() {

    val myBackGroundButton = remember {
        mutableStateOf(Color.Red)
    }

    val myButtonText = remember {
        mutableStateOf("Do my magic here")
    }

    val isButtonClicked = remember {
        mutableStateOf(false)
    }

    val onTextFieldC = remember {
        mutableStateOf("!")
    }

    val myImagePath = remember {
        mutableStateOf(R.drawable.test)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        myImagePath.value = if (isButtonClicked.value) R.drawable.test else R.drawable.test_02

        Image(
            painter = painterResource(id = myImagePath.value),
            contentDescription = "",
            modifier = Modifier.size(300.dp, 300.dp),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                isButtonClicked.value = !isButtonClicked.value

                if (!isButtonClicked.value) {
                    myBackGroundButton.value = Color.Green
                    myButtonText.value = "!"
                } else {
                    myBackGroundButton.value = Color.Red
                    myButtonText.value = onTextFieldC.value
                    onTextFieldC.value = ""
                }
            },
            modifier = Modifier.size(250.dp, 60.dp),
            colors = ButtonDefaults.buttonColors(myBackGroundButton.value),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                10.dp,
                Color.Black
            )
        ) {
            Text(
                text = myButtonText.value,
                color = Color.White,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(30.dp))
        }
        Spacer(modifier = Modifier.size(30.dp))

        TextField(value = onTextFieldC.value, onValueChange = {
            onTextFieldC.value = it
        },
            label = { Text(text = "Kiss my ass", color = Color.Red, fontSize = 15.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    My01stComposeProjectTheme {
        myComponents()
    }
}