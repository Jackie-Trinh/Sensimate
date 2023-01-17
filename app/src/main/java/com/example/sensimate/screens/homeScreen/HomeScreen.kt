package com.example.sensimate.screens.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.navigation.Graph
import com.example.sensimate.navigation.HomeNavGraph
import java.lang.reflect.Modifier

//without this it will say error, but the code still works (Scaffold)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//setting up the main screens, with a bottom bar and the main navigation graph
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(
        topBar = { TopBar(navController = navController)},
        bottomBar = {BottomBar(navController = navController)})
    {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun TopBar(navController: NavHostController){
    // Create a boolean variable
    // to store the display menu state
    var mDisplayMenu by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // fetching local context
    val mContext = LocalContext.current

    //screens with the top-bar visible
    val screensVisible = listOf(
        BottomBarScreen.MyEvents,
        BottomBarScreen.Discover,
        BottomBarScreen.Profile,
        BottomBarScreen.EventPage,
        BottomBarScreen.Survey,
        BottomBarScreen.EventManagerPage,
        BottomBarScreen.AboutUs,
        BottomBarScreen.FAQ,
        BottomBarScreen.Settings,
    )

    //for each screen on the visible list, show the top-bar
    val bottomBarDestination = screensVisible.any {it.route == currentDestination?.route}

    if(bottomBarDestination){
        // Creating a Top bar
        TopAppBar(
            title = {
                if (currentDestination != null) {
                    Text(currentDestination.route.toString(), color = Color.Black)
                }
            } ,
            backgroundColor = MaterialTheme.colors.surface,
            actions = {

                // Creating Icon button for dropdown menu
                IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                    Icon(Icons.Default.MoreVert, "")
                }

                // Creating a dropdown menu
                DropdownMenu(
                    expanded = mDisplayMenu,
                    onDismissRequest = { mDisplayMenu = false }
                ) {
                    DropdownMenuItem(onClick = {navController.navigate(
                        route = BottomBarScreen.FAQ.route)
                    mDisplayMenu = false}) {
                        Text(text = "FAQ")
                    }
                    DropdownMenuItem(onClick = {navController.navigate(
                        route = BottomBarScreen.AboutUs.route)
                        mDisplayMenu = false}) {
                        Text(text = "About us")
                    }
                    DropdownMenuItem(onClick = {navController.navigate(
                        route = BottomBarScreen.Settings.route)
                        mDisplayMenu = false}) {
                        Text(text = "Settings")
                    }
                    DropdownMenuItem(onClick = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                        mDisplayMenu = false
                    }) {
                        Text(text = "Logout")
                    }
                }
            }
        )
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
        BottomBarScreen.AboutUs,
        BottomBarScreen.FAQ,
        BottomBarScreen.Settings,
    )
    //bottom-bar current screen destination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //for each screen on the visible list, show the bottom-bar
    val bottomBarDestination = screensVisible.any {it.route == currentDestination?.route}
    //add items to the bottom-bar based on the screens list
    if (bottomBarDestination){
        BottomNavigation() {
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