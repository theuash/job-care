package com.jobcare.voice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jobcare.voice.ui.screens.*
import com.jobcare.voice.ui.theme.JobCareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JobCareApp() }
    }
}

@Composable
fun JobCareApp() {
    JobCareTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val nav = rememberNavController()
            NavHost(nav, startDestination = "dashboard") {
                composable("login") { LoginScreen(nav) }
                composable("dashboard") { DashboardScreen(nav) }
                composable("workers") { WorkersScreen(nav) }
                composable("workers/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) {
                    WorkerDetailScreen(nav, it.arguments?.getString("id") ?: "")
                }
                composable("jobs") { JobsScreen(nav) }
                composable("analytics") { AnalyticsScreen(nav) }
            }
        }
    }
}
