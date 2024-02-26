package uni.dev.doctorlink.screens.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import uni.dev.doctorlink.navigation.BottomNavScreen
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Primary

@Composable
fun BottomNavBar(bottomNavController: NavController) {
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(backgroundColor = Color.White, elevation = 0.dp) {
        BottomNavScreen.all().forEach {
            BottomNavigationItem(selected = currentRoute == it.route, onClick = {
                bottomNavController.navigate(it.route) {
                    popUpTo(bottomNavController.graph.id)
                }
            }, icon = {
                Icon(
                    if (currentRoute == it.route) painterResource(id = it.iconSelected) else painterResource(
                        id = it.iconNotSelected
                    ),
                    contentDescription = "",
                    tint = if (currentRoute == it.route) Primary else Gray,
                )
            })
        }
    }
}
