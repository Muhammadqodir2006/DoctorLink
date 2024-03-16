package uni.dev.doctorlink.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeView(welcomeViewModel: WelcomeViewModel) {
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(WelcomePages.size, modifier = Modifier.weight(1f), state = pagerState) { index ->
            OnboardingPageContent(WelcomePages[index])
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow {
                items(WelcomePages.size) { index ->
                    TabRowDefaults.Indicator(
                        color = if (pagerState.currentPage == index) Primary else Gray_2,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(6.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    if (pagerState.currentPage == WelcomePages.lastIndex) {
                        welcomeViewModel.navigate(context)
                    } else {
                        welcomeViewModel.nextPage(pagerState, coroutineScope)
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text(
                    text = if (pagerState.currentPage == WelcomePages.lastIndex) "Boshlash" else "Keyingi",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

    }
}

@Composable
fun OnboardingPageContent(welcomePage: WelcomePage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = welcomePage.image), // Update with your image resource
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth().aspectRatio(1f).padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = welcomePage.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Text2,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = welcomePage.description,
            fontSize = 16.sp,
            color = Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}