package uni.dev.doctorlink.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uni.dev.doctorlink.ui.theme.Background

@Composable
fun HomeView(vm: HomeViewModel) {
    val dialogOpen = vm.regionDialogOpen.observeAsState().value!!
    val searchState = vm.layoutState.observeAsState().value!!

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(visible = searchState == 0, modifier = Modifier.fillMaxWidth()) {
            HomeTopBar(vm)
        }
        HomeSearchField(vm)
        AnimatedVisibility(visible = searchState == 1) {
            SearchLayout(vm)
        }
        AnimatedVisibility(visible = searchState == 0) {
            MainLayout(vm = vm)
        }
        AnimatedVisibility(visible = searchState == 2) {
            SecondLayout(vm = vm)
        }
    }
    AnimatedVisibility(visible = dialogOpen) {
        HomeDialog(vm = vm)
    }
}


