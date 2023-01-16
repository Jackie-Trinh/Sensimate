package com.example.sensimate.screens.homeScreen

import android.annotation.SuppressLint
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

//setting up the main screens, with a bottom bar and the main navigation graph
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(bottomBar = {BottomBar(navController = navController)})
    {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    //bottom-bar items
    val screens = listOf(
        BottomBarScreen.MyEvents,
        BottomBarScreen.Discover,
        BottomBarScreen.Profile,
    )
    //screens with the bottom-bar visible
    val screensVisible = listOf(
        BottomBarScreen.MyEvents,
        BottomBarScreen.Discover,
        BottomBarScreen.Profile,
        BottomBarScreen.EventPage,
        BottomBarScreen.Survey,
        BottomBarScreen.EventManagerPage,
    )
    //bottom-bar current screen destination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //for each screen on the visible list, show the bottom-bar
    val bottomBarDestination = screensVisible.any {it.route == currentDestination?.route}
    //add items to the bottom-bar based on the screens list
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

//add items to bottom-bar from the BottomBarScreen.
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