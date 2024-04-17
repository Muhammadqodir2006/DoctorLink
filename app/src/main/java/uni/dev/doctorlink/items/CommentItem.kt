package uni.dev.doctorlink.items

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.model.Comment
import uni.dev.doctorlink.model.User
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2
import uni.dev.doctorlink.util.Api

//@Preview
//@Composable
//fun PrvCommentItem() {
//    Column(Modifier.padding(horizontal = 16.dp)) {
//        for (i in 1..10) CommentItem(comment = Comment("", "", "", "", 0))
//    }
//}

@Composable
fun CommentItem(comment: Comment) {
    val userName = remember {
        mutableStateOf("")
    }
    Api.getUserByKey(comment.userKey!!) { user ->
        Log.d("TAG", "CommentItem: $user")
        userName.value = user.name + " " + user.surname
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = userName.value, color = Text2, fontWeight = FontWeight.W600, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Row {
                for (i in 1..5) Icon(
                    painter = painterResource(id = R.drawable.star_fill),
                    contentDescription = "",
                    tint = if (i <= comment.rating!!) Primary else Gray_3,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(horizontal = 1.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = comment.text!!, color = Gray)
    }
}