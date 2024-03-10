package uni.dev.doctorlink.screens.welcome

import android.content.Context
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.SharedHelper

class WelcomeViewModel(val navController: NavController) {
    @OptIn(ExperimentalPagerApi::class)
    fun nextPage(pagerState: PagerState, coroutineScope: CoroutineScope) {
        val i = pagerState.currentPage + 1
        coroutineScope.launch {
            pagerState.animateScrollToPage(i, 0f)
        }
    }
    fun navigate(context: Context) {
        val sharedHelper = SharedHelper(context)
        sharedHelper.setWelcome()
        navController.navigate(Screen.TelegramUser.route){
            popUpTo(navController.graph.id)
        }
    }
}