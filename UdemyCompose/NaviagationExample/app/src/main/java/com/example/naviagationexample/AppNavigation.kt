package com.example.naviagationexample

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.naviagationexample.pages.SecondPage
import com.example.naviagationexample.pages.MainPage
import com.example.naviagationexample.pages.Pages

@Composable
fun MyNav() {
    val myNavController = rememberNavController()

    NavHost(navController = myNavController, startDestination = Pages.MAIN_PAIGE.aName) {
        composable(route = Pages.MAIN_PAIGE.aName) {
            MainPage(myNavController)
        }
        composable(
            route = Pages.SECOND_PAGE.aName + "/{aName}/{anAge}",
            arguments = listOf(
                navArgument("aName") { type = NavType.StringType },
                navArgument("anAge") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("aName")
            val age = navBackStackEntry.arguments?.getInt("anAge")
            val aData = Pair<String?, Int?>(name, age)

            SecondPage(myNavController, aData)
        }
    }
}