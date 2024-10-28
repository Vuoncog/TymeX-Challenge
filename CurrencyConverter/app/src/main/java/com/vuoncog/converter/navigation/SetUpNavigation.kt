package com.vuoncog.converter.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vuoncog.converter.navigation.models.NavigationRoute.HOME
import com.vuoncog.converter.ui.screens.home.HomeScreen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SetUpNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(route = HOME){
            HomeScreen()
        }
    }
}