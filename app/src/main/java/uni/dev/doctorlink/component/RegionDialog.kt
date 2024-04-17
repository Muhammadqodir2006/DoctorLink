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
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.util.Api

@Composable
fun RegionDialog(onClick: (reg: Region) -> Unit, onDismiss: () -> Unit, region: Region) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        confirmButton = {},
        text = {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(Api.getRegions().size) {
                    val reg = Api.getRegions()[it]
                    Column(
                        modifier = Modifier
                            .background(
                                color = if (region == reg) Gray_4 else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onClick(reg)
                            }
                    ) {
                        Text(
                            text = reg.name!!,
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
