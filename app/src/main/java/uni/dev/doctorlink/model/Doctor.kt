package uni.dev.doctorlink.model

data class Doctor(
    var key: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var image:String? = null,
    var gender: Boolean? = null,
    var rating: Float? = 0f,
    var price: Int? = null,
    var yearStarted: Int? = null,
    var specialty: List<Int>? = null,
    var info: String? = null,
    var hospitalKey: String? = null,
    var regionId: Int? = null
)