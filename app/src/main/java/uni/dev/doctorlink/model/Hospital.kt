package uni.dev.doctorlink.model

data class Hospital(
    var key: String? = null,
    var name: String? = null,
    var regionId: Int? = null,
    var phone: List<String>? = null,
    var schedule: String? = null,
    var photo: List<String>? = null
)