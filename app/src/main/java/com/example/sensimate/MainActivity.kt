package com.example.sensimate

//base document imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
//navbar imports
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.sensimate.screens.Discover
import com.example.sensimate.screens.MyEvents
import com.example.sensimate.screens.Profile
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.sensimate.screens.Login
import com.example.sensimate.ui.theme.SensimateTheme
//other


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensimateTheme(darkTheme = false) {
            MainScreen()
            }
        }
    }
}

//main screen setup
@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = {Text("Sensimate")})  },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController)}
    )
}

//Bottom nav bar inserts
@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.route,
    ) {
        composable(NavRoutes.MyEvents.route) {
            MyEvents()
        }

        composable(NavRoutes.Discover.route) {
            Discover()
        }

        composable(NavRoutes.Profile.route) {
            Profile()
        }
        composable(NavRoutes.Login.route) {
            Login()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}

//profile inserts