package com.example.naviagationexample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(
    aClick: () -> Unit,
) {
    Button(
        onClick = aClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.purple_500)
        ),
        modifier = Modifier.size(
            width = 300.dp,
            height = 60.dp
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Yellow
        )
    ) {
        Text(
            "Send data",
            fontSize = 16.sp,
            color = Color.Green
        )
    }
}