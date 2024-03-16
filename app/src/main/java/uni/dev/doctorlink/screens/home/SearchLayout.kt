package uni.dev.doctorlink.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Text
import uni.dev.doctorlink.ui.theme.Text2_2

@Composable
fun SearchLayout(vm: HomeViewModel){
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        val searchHistory =
            listOf(
                "stomatolog",
                "kardiolog",
                "Ahror Valiyev",
                "Azim Karimovich",
                "kardiolog",
                "Ahror Valiyev",
                "Azim Karimovich",
                "kardiolog",
                "Ahror Valiyev",
            )
        Spacer(modifier = Modifier.height(6.dp))
        Column {
            Text(text = "Yaqindagi", fontWeight = FontWeight.W600, modifier = Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn {
                items(searchHistory.size) {
                    Column {
                        Text(
                            text = "  ${searchHistory[it]}",
                            modifier = Modifier
                                .clickable { }
                                .clip(RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                                .background(Gray_4)
                                .padding(vertical = 10.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            fontSize = 16.sp,
                            color = Text
                        )
                        HorizontalDivider()
                    }
                }
            }
        }

    }

}