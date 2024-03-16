package uni.dev.doctorlink.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2

@Composable
fun HomeTopBar(
    vm: HomeViewModel
) {
    val location = vm.location.observeAsState().value!!
    val notificationCount = vm.notificationCount.observeAsState().value!!
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            Icon(
                Icons.Rounded.AccountCircle,
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { vm.topLeftButtonAction() }
                    .size(32.dp),
                tint = Gray
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    vm.openDialog()
                    vm.updateDialogContent(0)
                }) {
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                Icons.Rounded.LocationOn,
                contentDescription = "",
                tint = Primary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = location,
                color = Text2,
                modifier = Modifier.padding(vertical = 6.dp),
                fontWeight = FontWeight.W700
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "", tint = Primary)
            Spacer(modifier = Modifier.width(6.dp))
        }
        Box {
            Icon(painterResource(id = if (notificationCount == 0) R.drawable.notification else R.drawable.notification_fill),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp)
                    .clickable { vm.navController.navigate(Screen.Notifications.route) }
                    .padding(4.dp),
                tint = if (notificationCount == 0) Gray else Primary)
            if (notificationCount > 0) {
                Text(
                    text = notificationCount.toString(),
                    modifier = Modifier
                        .align(Alignment.Center),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W900,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }

}