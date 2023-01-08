package com.example.sensimate

//base document imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
//navbar imports
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.sensimate.navigation.NavBarItems
import com.example.sensimate.navigation.RootNavGraph
import com.example.sensimate.navigation.SetupNavGraph
import com.example.sensimate.ui.theme.SensimateTheme
//other


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //navController = rememberNavController() - previous setup for nav call
            //SetupNavGraph(navController = navController) - previous setup for nav call
            SensimateTheme(darkTheme = false) {
                RootNavGraph(navController = rememberNavController())
            //MainScreen() - previous setup for nav call
            }
        }
    }
}


/*

//main screen setup
@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        //topBar = { TopAppBar(title = {Text("Sensimate")})  },
        content = { SetupNavGraph(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController)}
    )

}



//Bottom nav bar inserts

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


 */

//profile inserts