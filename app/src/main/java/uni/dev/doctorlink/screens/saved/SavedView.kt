package uni.dev.doctorlink.screens.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.ui.theme.Text
import uni.dev.doctorlink.ui.theme.Text_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedView(savedViewModel: SavedViewModel) {
    Column (Modifier.background(Color.Unspecified)) {
        TopAppBar(title = {
            Text(
                text = "Saqlanganlar",
                fontSize = 20.sp,
                color = Text_2,
                fontWeight = FontWeight.W700
            )
        } )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn (modifier = Modifier.padding(horizontal = 12.dp)){
            items(12) {
                DoctorItem()
            }
        }
    }
}