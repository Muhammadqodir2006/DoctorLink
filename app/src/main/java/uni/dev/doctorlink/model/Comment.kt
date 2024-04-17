package uni.dev.doctorlink.model

data class Comment(
    var key: String? = null,
    val userKey: String? = null,
    val doctorKey: String? = null,
    val text: String? = null,
    val rating: Int? = null
)