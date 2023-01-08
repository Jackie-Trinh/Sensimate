package com.example.sensimate.screens.homeScreen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.navigation.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(bottomBar = {BottomBar(navController = navController)}) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.MyEvents,
        BottomBarScreen.Discover,
        BottomBarScreen.Profile,
    )
    val screensVisible = listOf(
        BottomBarScreen.MyEvents,
        BottomBarScreen.Discover,
        BottomBarScreen.Profile,
        BottomBarScreen.EventPage,
        BottomBarScreen.Survey,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screensVisible.any {it.route == currentDestination?.route}
    if (bottomBarDestination){
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController){
    BottomNavigationItem(
        label = { Text(text = screen.title)},
        icon = { Icon(imageVector = screen.icon,
            contentDescription = "Navigation Icon" )},
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}