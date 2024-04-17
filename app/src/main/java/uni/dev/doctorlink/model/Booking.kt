package uni.dev.doctorlink.model

data class Booking(
    var key: String? = null,
    val userKey: String? = null,
    val doctorKey: String? = null,
    var date: String? = null,
    var time: String? = null,
    var commented: Boolean? = false
)