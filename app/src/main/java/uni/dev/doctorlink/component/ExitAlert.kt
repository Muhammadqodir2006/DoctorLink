package uni.dev.doctorlink.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Red

@Composable
fun ExitDialog(onDismiss:() -> Unit, onConfirm:()->Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.textButtonColors(containerColor = Red)
            ) {
                Text(text = "Ha", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, shape = RoundedCornerShape(12.dp)) {
                Text(text = "Yo'q")
            }
        },
        containerColor = Color.White,
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alert),
                    modifier = Modifier.size(64.dp),
                    contentDescription = "",
                    tint = Red
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Chiqishni xohlaysizmi?")
            }
        },
    )

}