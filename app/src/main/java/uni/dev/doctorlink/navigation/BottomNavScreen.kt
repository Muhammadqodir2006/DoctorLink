package uni.dev.doctorlink.navigation

import uni.dev.doctorlink.R

sealed class BottomNavScreen(
    val route: String,
    val iconNotSelected: Int,
    val iconSelected: Int,
    val label: String
) {
    object Home : BottomNavScreen("home", R.drawable.home_outline, R.drawable.home_fill, "Home")
    object Saved :
        BottomNavScreen("saved", R.drawable.saved_outline, R.drawable.saved_fill, "Explore")

    object Bookings :
        BottomNavScreen("bookings", R.drawable.list_outline, R.drawable.list_fill, "Bookings")

    object Profile :
        BottomNavScreen("profile", R.drawable.profile_outline, R.drawable.profile_fill, "Profile")

    companion object {
        fun all(): List<BottomNavScreen> {
            return listOf(Home, Bookings, Saved, Profile)
        }
    }
}