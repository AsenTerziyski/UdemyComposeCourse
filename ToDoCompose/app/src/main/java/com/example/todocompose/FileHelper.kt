package com.example.todocompose

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.io.FileNotFoundException
import java.io.ObjectInput
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

const val FILE_NAME = "todoList.txt"

fun writeMyData(items: SnapshotStateList<String>, context: Context) {
    val fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
    val oas = ObjectOutputStream(fos)
    val itemList = ArrayList<String>()
    itemList.addAll(items)
    oas.writeObject(itemList)
    oas.close()
}

fun readMyData(context: Context): SnapshotStateList<String> {
    val itemList: ArrayList<String>
    return try {
        val fis = context.openFileInput(FILE_NAME)
        val ois = ObjectInputStream(fis)
        itemList = ois.readObject() as ArrayList<String>

        val items = SnapshotStateList<String>()
        items.addAll(itemList)

        items
    } catch (exception: FileNotFoundException) {
        Log.d("FileDoesNotExistsError", exception.message.toString())
        SnapshotStateList()
    }
}