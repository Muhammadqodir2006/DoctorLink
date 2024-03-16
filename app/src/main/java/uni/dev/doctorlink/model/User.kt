package uni.dev.doctorlink.model

data class User(
    val id: String,
    var username:String,
    var name: String,
    var surname: String,
    var gender: Boolean,
    var birthday: String,
    var region: String
)