package uni.dev.doctorlink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.ui.theme.Gray_4
import java.time.LocalDate

@Composable
fun BirthdayDialog(onClick: (year: Int) -> Unit, onDismiss: () -> Unit, birthYear: Int) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        confirmButton = {},
        text = {
            val currentYear = LocalDate.now().year - 18
            LazyColumn(Modifier.fillMaxWidth()) {
                items(80) {
                    val year = currentYear - it
                    Column(
                        modifier = Modifier
                            .background(
                                color = if (birthYear == year) Gray_4 else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onClick(year)
                            }
                    ) {
                        Text(
                            text = (year).toString() + "-yil",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            thickness = 1.dp,
                            color = Gray_4
                        )
                    }
                }
            }
        }
    )
}
