package uni.dev.doctorlink.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2_5
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Gray_5
import uni.dev.doctorlink.ui.theme.Primary

@Composable
fun HomeSearchField(vm: HomeViewModel) {
    val searchText = vm.searchText.observeAsState().value!!
    val focusManager = LocalFocusManager.current
    val layoutState = vm.layoutState.observeAsState().value!!

    val searchBarDp = animateDpAsState(
        targetValue = if (layoutState == 0) 16.dp else 4.dp,
        label = ""
    )
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AnimatedVisibility(visible = layoutState == 2) {
            IconButton(onClick = { vm.closeSecond() }) {
                Icon(painterResource(id = R.drawable.arrow_back), contentDescription = "")
            }
        }
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.hasFocus) vm.openSearch()
                else vm.closeSearch()
            }
            .padding(horizontal = searchBarDp.value, vertical = searchBarDp.value)
            .border(1.5.dp, Color.Transparent, RoundedCornerShape(16.dp)),
            value = searchText,
            onValueChange = { vm.updateSearchText(it) },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Gray_5,
                unfocusedContainerColor = Gray_3,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Primary
            ),
            placeholder = { Text(text = "Qidirish", color = Gray) },
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {
                vm.search(searchText)
            }),
            leadingIcon = {
                Icon(painterResource(id = R.drawable.search), contentDescription = "", tint = Gray)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            trailingIcon = {
                AnimatedVisibility(visible = layoutState == 1) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "",
                        tint = Gray,
                        modifier = Modifier
                            .background(
                                Gray_2_5, CircleShape
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                            .clickable {
                                vm.updateSearchText("")
                                focusManager.clearFocus()
                                vm.closeSearch()
                            })
                }
            })
    }

}
