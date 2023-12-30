package com.example.my03thcomposeproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my03thcomposeproject.ui.theme.My03thComposeProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My03thComposeProjectTheme {
//                RadioButtonsExample(true)
//                SwitchDemo()
//                DropdownMenuExample()
//                ToastMessageExample()
//                SnackBarExample()
//                DialogMessageExample()
                TopBarExample()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarExample() {

    var actionText by remember {
        mutableStateOf("Action text here!")
    }

    var menuState by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "MalmSuite", fontSize = 24.sp)
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = "suite", fontSize = 16.sp)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            actionText = "Menu item clicked"
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            actionText = "Share item clicked"
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = {
                            actionText = "Search item clicked"
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = {
                            menuState = true
                            actionText = "More item clicked"
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = ""
                        )
                        DropdownMenu(
                            expanded = menuState,
                            onDismissRequest = {
                                menuState = false
                            }
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Settings") },
                                onClick = {
                                    actionText = "Settings clicked"
                                    menuState = false
                                })
                            DropdownMenuItem(
                                text = { Text(text = "Other") },
                                onClick = {
                                    actionText = "Others clicked"
                                    menuState = false
                                })
                        }
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = actionText, fontSize = 32.sp, modifier = Modifier.clickable {
                    actionText = "Action text!"
                })
            }
        })
}

@Composable
private fun DialogMessageExample() {

    var textButton by remember {
        mutableStateOf("Show dialog message")
    }

    var dialogStatus by remember {
        mutableStateOf(false)
    }

    var textColor by remember {
        mutableStateOf(Color.White)
    }

    val myContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            dialogStatus = true
        }) {
            Text(
                text = textButton, color = textColor
            )
        }

        if (dialogStatus) {
            AlertDialog(
                onDismissRequest = { dialogStatus = true },
                confirmButton = {
                    Button(onClick = {
                        dialogStatus = false
                        textColor = Color.Cyan
                        textButton = "Gaden chalgar"
                    }) {
                        Text(text = "No", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        dialogStatus = false
                        textColor = Color.Magenta
                        Toast.makeText(
                            myContext,
                            "Congrats! You are a truly havy metal lover :)",
                            Toast.LENGTH_SHORT
                        ).show()
                        textButton = "HEAVY METAL |_|"
                    }) {
                        Text(text = "Yes", color = Color.White)
                    }
                },

                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.allert_dialog_icon),
                        contentDescription = "Alert dialog icon",
                    )
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dropdown_icon),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "Kiss my ass!",
                            color = Color.Blue,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center
                        )
                    }

                },
                text = {
                    Text(
                        text = "Do you love heavy metal?",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp
                    )
                },
                containerColor = Color.White,

                )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SnackBarExample() {

        val myContext = LocalContext.current

        val mySnackBarHostState = remember {
            SnackbarHostState()
        }

        val myCoroutineScope = rememberCoroutineScope()

        Scaffold(snackbarHost = {
            SnackbarHost(
                hostState = mySnackBarHostState
            ) { snackBarData ->
                Snackbar(
                    snackbarData = snackBarData,
                    containerColor = Color.Red,
                    contentColor = Color.Cyan,
                    actionColor = Color.Yellow,
                    dismissActionContentColor = Color.Blue

                )
            }
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        myCoroutineScope.launch {

                            val result = mySnackBarHostState.showSnackbar(
                                message = "This is the snack bar message.",
                                actionLabel = "Show Toast",
                                duration = SnackbarDuration.Indefinite,
                                withDismissAction = true
                            )

                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    Toast.makeText(
                                        myContext, "Hello!", Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    //do nothing
                                }
                            }
                        }
                    },
                ) {
                    Text(text = "Show snack bar message bellow!")
                }
            }
        })

    }

    @Composable
    fun ToastMessageExample() {

        val myContext = LocalContext.current
        var counter by remember {
            mutableStateOf(0)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    if (counter <= 3) {
                        counter++
                    } else {
                        counter = 0
                    }
                    Toast.makeText(
                        myContext, "-> Count: $counter <-", Toast.LENGTH_LONG
                    ).show()
                },
            ) {
                Text(text = "Show toast message.")
            }
        }
    }

    @Composable
    fun DropdownMenuExample() {

        var dropDownStatus by remember {
            mutableStateOf(false)
        }

        val itemList = listOf("Germany", "Bulgaria", "Romania")

        var itemPosition by remember {
            mutableStateOf(0)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Box() {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        dropDownStatus = true
                    }) {
                    Text(text = itemList[itemPosition], modifier = Modifier.clickable {
                        dropDownStatus = true
                    })
                    Image(
                        painter = painterResource(id = R.drawable.dropdown_icon),
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = dropDownStatus, onDismissRequest = {
                    dropDownStatus = true
                }) {
                    itemList.forEachIndexed { index, country ->
                        DropdownMenuItem(text = {
                            Text(text = country)
                        }, onClick = {
                            itemPosition = index
                            dropDownStatus = false
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun SwitchDemo() {
        var switchState by remember {
            mutableStateOf(false)
        }
        var textContent by remember {
            mutableStateOf("Kiss my ass!")
        }
        var myAlphaValue by remember {
            mutableStateOf(1F)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(50.dp))

            Switch(
                checked = switchState, onCheckedChange = {
                    switchState = it
                }, colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Blue,
                    uncheckedThumbColor = Color.Blue,
                    checkedTrackColor = Color.Red,
                    uncheckedTrackColor = Color.Cyan,
                )
            )

            Spacer(modifier = Modifier.size(30.dp))

            textContent = if (switchState) {
                myAlphaValue = 1F
                "Do not kiss my ass!"
            } else {
                myAlphaValue = 0F
                "Muaaa :)"
            }

            Image(
                painter = painterResource(id = R.drawable.switch_demo),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
                    .alpha(myAlphaValue),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.size(30.dp))

            Text(
                text = textContent,
                modifier = Modifier
                    .background(Color.Yellow)
                    .size(300.dp, height = 75.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        }
    }

    @Composable
    fun RadioButtonsExample(test: Boolean) {
        val backGround = remember {
            mutableStateOf(Color.White)
        }
        val radioIndex = remember {
            mutableStateOf(0)
        }
        val radioTexts = listOf("Red", "Green", "Blue")
        val colorList = listOf(Color.Red, Color.Green, Color.Blue)
        var test by remember {
            mutableStateOf(true)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backGround.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            Column {
                radioTexts.forEachIndexed { position, colorName ->
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            radioIndex.value = position
                        }) {
                        RadioButton(
                            selected = colorName == radioTexts[radioIndex.value], onClick = {
                                radioIndex.value = position
                            }, colors = RadioButtonDefaults.colors(Color.Blue)
                        )
                        Text(
                            text = colorName,
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (radioIndex.value < radioTexts.size) {
                        Spacer(modifier = Modifier.size(55.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.size(75.dp))
            Button(
                onClick = {
                    backGround.value = colorList[radioIndex.value]
                },
            ) {}
        }
    }

    //    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        My03thComposeProjectTheme {
//        RadioButtonsExample(true)
//        SwitchDemo()
//        DropdownMenuExample()
        }
    }
}