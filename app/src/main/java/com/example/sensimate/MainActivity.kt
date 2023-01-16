package com.example.sensimate

//base document imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//navbar imports
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.sensimate.navigation.RootNavGraph
import com.example.sensimate.ui.theme.SensimateTheme
import dagger.hilt.android.AndroidEntryPoint

//other

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensimateTheme(darkTheme = false) {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}
