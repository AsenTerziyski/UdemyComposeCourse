package com.example.naviagationexample.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.naviagationexample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(
    navController: NavController,
    data: Pair<String?, Int?>
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Second page", fontSize = 20.sp, color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow Back icon")
                    }
                }
            )
        },
        content = { paddingValue ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                data.first?.let {
                    Text(text = "Name: $it", color = Color.Blue, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.size(30.dp))

                data.second?.let {
                    Text(text = "Age: $it", color = Color.Blue, fontSize = 20.sp)
                }

            }
        }
    )
}