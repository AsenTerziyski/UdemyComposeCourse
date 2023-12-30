package com.example.lazycolumnexample.pages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lazycolumnexample.R
import com.example.lazycolumnexample.data.retrieveCountries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyColumnFirstPage(navController: NavController) {

    val countryList = retrieveCountries()
    val myContext = LocalContext.current

    val topAppBarBehavior = TopAppBarDefaults.pinnedScrollBehavior()


    Scaffold(
        modifier = Modifier
            .nestedScroll(topAppBarBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Countries", fontSize = 20.sp)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color.Green
                ),
                scrollBehavior = topAppBarBehavior
            )
        },

        content = {
            // Add here lazy column
            LazyColumn(
                modifier = Modifier.padding(it),
                content = {

                    items(
                        count = countryList.size,
                        itemContent = { index ->
                            val country = countryList[index]


                            Card(
                                onClick = {
                                    Toast.makeText(
                                        myContext,
                                        country.countryName,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(7.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(id = R.color.purple_500)
                                ),
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(6.dp),
                                border = BorderStroke(2.dp, color = Color.Red)

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(7.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        // image
                                        Image(
                                            painter = painterResource(id = country.countryImage),
                                            contentDescription = country.countryName,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(
                                                    RoundedCornerShape(
                                                        100
                                                    )
                                                )
                                                .border(
                                                    2.dp, Color.Red, RoundedCornerShape(100)
                                                ),
                                            contentScale = ContentScale.Crop,
                                            alignment = Alignment.Center
                                        )

                                        // text
                                        Column(Modifier.padding(10.dp)) {
                                            Text(
                                                text = country.countryName,
                                                fontSize = 20.sp,
                                                color = Color.White
                                            )
                                            Spacer(modifier = Modifier.size(3.dp))
                                            Text(
                                                text = country.countryDetail,
                                                fontSize = 16.sp,
                                                color = Color.Green
                                            )
                                        }
                                    }
                                    Button(
                                        onClick = {
                                            // DO something here!
                                            val data = "/${country.countryId}"
                                            navController.navigate(Pages.LAZY_COLUMN_SECOND_PAGE.page + data)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White,
                                        ),
                                        border = BorderStroke(2.dp, color = Color.Red)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.ArrowForward,
                                            contentDescription = "",
                                            tint = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    )
                },
            )
        }
    )
}