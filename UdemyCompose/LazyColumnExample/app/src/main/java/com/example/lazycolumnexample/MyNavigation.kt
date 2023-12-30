package com.example.lazycolumnexample

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lazycolumnexample.pages.LazyRowFirstPage
import com.example.lazycolumnexample.pages.LazyRowSecondPage
import com.example.lazycolumnexample.pages.Pages

@Composable
fun MyNavigation() {

    val myNavController = rememberNavController()

    NavHost(
        navController = myNavController,
        startDestination = Pages.LAZY_COLUMN_FIRST_PAGE.page
    ) {


        // LazyColumnDemo:
//        composable(
//            route = Pages.LAZY_COLUMN_FIRST_PAGE.page
//        ) {
//            LazyColumnFirstPage(navController = myNavController)
//        }
//
//        composable(
//            route = Pages.LAZY_COLUMN_SECOND_PAGE.page + "/{id}",
//            arguments = listOf(
//                navArgument("id") {
//                    type = NavType.IntType
//                }
//            )
//        ) {
//            val countryId = it.arguments?.getInt("id")
//
//            countryId?.let { id ->
//                LazyColumnSecondPage(
//                    navController = myNavController,
//                    id = id
//                )
//            }
//        }

        //LazyRowDemo
        composable(
            route = Pages.LAZY_COLUMN_FIRST_PAGE.page
        ) {
            LazyRowFirstPage(navController = myNavController)
        }

        composable(
            route = Pages.LAZY_COLUMN_SECOND_PAGE.page + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val countryId = it.arguments?.getInt("id")

            countryId?.let { id ->
                LazyRowSecondPage(
                    navController = myNavController,
                    id = id
                )
            }
        }
    }
}