package uni.dev.doctorlink.screens.welcome

import uni.dev.doctorlink.R

data class WelcomePage(
    val title: String,
    val description: String,
    val image: Int
)

val WelcomePages = listOf(
    WelcomePage(
        "Shifokor qabuliga yozilish",
        "Eng yaxshi shifokorlarni toping va qabulga onlayn yoziling.",
        R.drawable.welcome1
    ),
    WelcomePage(
        "Tibbiy ko'riklar jadvali",
        "Barcha tibbiy ko'riklaringizni bir joyda kuzatib boring.",
        R.drawable.welcome2
    ),
    WelcomePage(
        "Ilova bildirishnomalari",
        "Eslatma va bildirishnomalar bilan tibbiy ko'riklarni hech qachon o'tkazib yubormang.",
        R.drawable.welcome3
    ),
)
