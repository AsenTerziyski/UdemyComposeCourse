package com.example.todocompose

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {

    val myContext = LocalContext.current

    var todoName by remember {
        mutableStateOf("")
    }

    val itemList = readMyData(myContext)

    val focusManager = LocalFocusManager.current

    var deleteDialogStatus by remember {
        mutableStateOf(false)
    }

    var updateDialogStatus by remember {
        mutableStateOf(false)
    }

    var clickedItemIndex by remember {
        mutableStateOf(-1)
    }

    var clickedItem by remember {
        mutableStateOf("")
    }

    var textDialogStatus by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = todoName,
                onValueChange = { userInput ->
                    todoName = userInput
                },
                label = {
                    Text(
                        text = "Enter TODO"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Yellow,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = colorResource(id = R.color.my_green),
                    containerColor = MaterialTheme.colorScheme.primary,
                    textColor = Color.Gray,
                    cursorColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .border(2.dp, Color.White, RoundedCornerShape(5.dp))
                    .weight(7F)
                    .height(60.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            Button(
                onClick = {
                    if (todoName.isNotBlank()) {
                        itemList.add(todoName)
                        writeMyData(itemList, myContext)
                        todoName = ""
                        focusManager.clearFocus()
                    } else {
                        Toast.makeText(myContext, "Please enter TODO!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .weight(3F)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.my_green),
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.Green
                )
            ) {
                Text(text = "Add", fontSize = 20.sp)
            }
        }

        LazyColumn(
            content = {
                items(
                    count = itemList.size,
                    itemContent = { anIndex ->
                        val item = itemList[anIndex]

                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .width(300.dp)
                                        .clickable {
                                            clickedItem = item
                                            textDialogStatus = true
                                        }
                                )

                                //Row dedicated to the icons
                                Row {
                                    IconButton(
                                        onClick = {
                                            updateDialogStatus = true
                                            clickedItemIndex = anIndex
                                            clickedItem = item
                                        },
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Edit,
                                            contentDescription = "edit",
                                            tint = Color.White
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            deleteDialogStatus = true
                                            clickedItemIndex = anIndex
                                        },
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "delete",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        )

        if (deleteDialogStatus) {
            AlertDialog(
                onDismissRequest = { deleteDialogStatus = false },
                title = {
                    Text("Delete")
                },
                text = {
                    Text(text = "Do you wanna delete this from TODOs list?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            itemList.removeAt(clickedItemIndex)

                            writeMyData(
                                items = itemList,
                                context = myContext
                            )

                            deleteDialogStatus = false

                            Toast.makeText(
                                myContext,
                                "Todo item No${clickedItemIndex + 1} deleted!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }) {
                        Text(text = "YES")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            deleteDialogStatus = false
                        }
                    ) {
                        Text(text = "NO")
                    }
                }
            )
        }

        if (updateDialogStatus) {
            AlertDialog(
                onDismissRequest = { updateDialogStatus = false },
                title = {
                    Text("Update TODO")
                },
                text = {
                    TextField(
                        value = clickedItem,
                        onValueChange = {
                            clickedItem = it
                        })
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            itemList[clickedItemIndex] = clickedItem
                            writeMyData(
                                items = itemList,
                                context = myContext
                            )

                            updateDialogStatus = false

                            Toast.makeText(
                                myContext,
                                "Todo item No${clickedItemIndex + 1} updated!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }) {
                        Text(text = "YES")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            updateDialogStatus = false
                        }
                    ) {
                        Text(text = "NO")
                    }
                }
            )
        }

        if (textDialogStatus) {
            AlertDialog(
                onDismissRequest = { textDialogStatus = false },
                title = {
                    Text("  TODO")
                },
                text = {
                    Text(text = clickedItem)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            textDialogStatus = false
                        }) {
                        Text(text = "OKAY")
                    }
                },
            )
        }
    }
}