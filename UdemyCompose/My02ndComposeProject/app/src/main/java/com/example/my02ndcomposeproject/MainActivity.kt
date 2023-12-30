package com.example.my02ndcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my02ndcomposeproject.ui.theme.My02ndComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My02ndComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckBoxExample()
                }
            }
        }
    }
}

@Composable
fun CheckBoxExample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF027CDD)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val resultText = remember {
            mutableStateOf("What is you gender?")
        }

        val isFirstCheckBoxChecked = remember {
            mutableStateOf(false)
        }
        val isSecondCheckBoxChecked = remember {
            mutableStateOf(false)
        }

        if (isFirstCheckBoxChecked.value) {
            resultText.value = "MALE"
        } else if (isSecondCheckBoxChecked.value) {
            resultText.value = "FEMALE"
        } else if (!isSecondCheckBoxChecked.value && !isFirstCheckBoxChecked.value) {
            resultText.value = "What is your gender?"
        }

        Spacer(modifier = Modifier.size(100.dp))
        Text(
            text = resultText.value,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFFFFA000))
                .padding(top = 10.dp, bottom = 10.dp)
                .size(300.dp, 100.dp),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(30.dp))

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = isFirstCheckBoxChecked.value,
                    onCheckedChange = {
                        isFirstCheckBoxChecked.value = it
                        isSecondCheckBoxChecked.value = !it
                    })
                Text(text = "Male", color = Color.White)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = isSecondCheckBoxChecked.value,
                    onCheckedChange = {
                        isSecondCheckBoxChecked.value = it
                        isFirstCheckBoxChecked.value = !it
                    })
                Text(text = "Female", color = Color.White)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    My02ndComposeProjectTheme {
        CheckBoxExample()
    }
}