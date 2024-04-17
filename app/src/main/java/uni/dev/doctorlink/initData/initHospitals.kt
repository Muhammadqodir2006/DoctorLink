package uni.dev.doctorlink.initData

import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.util.Api
import kotlin.random.Random

val names = listOf(
    "Markaziy shifoxona",
    "Oilaviy poliklinika",
    "Ko'p tarmoqli poliklinika",
    "Xususiy klinika",
    "Xususiy poliklinika",
    "Shahar shifoxonasi",
    "Klinik shifoxona",
    "Klinik kasalxona",
    "Tibbiyot markazi"
)
val schedule1 = listOf(
    "08:00", "08:30", "09:00", "09:30", "10:00"
)
val schedule2 = listOf(
    "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"
)
val schedule3 = listOf(
    "Du-Ju", "Du-Sha"
)
val photos = listOf(
    "https://xabar.uz/static/crop/3/9/920__95_3990307739.jpg",
    "https://www.toshvilstat.uz/images/yangiliklar2022/12.10.2022.jpg",
    "https://storage.kun.uz/source/6/uH-v0GeYxCmGJqnwNE8QgOdUAs2EqjnJ.jpg",
    "https://xs.uz/upload/post/2020/12/30/087c98471cdf4a7c2511b72a5909cb5f1230.jpg",
    "https://storage.kun.uz/source/1/DPYd5jeTnIZkwm8-GGE8NhYU9rrW9zRp.jpg",
    "https://static.review.uz/crop/5/7/1400__100_571613534.jpg?v=1596556795",
    "https://aab.uz/upload/iblock/43f/43f0638c43fe7948e43352e8b861741b.jpg",
    "https://istmedic.de/v1/uploads/acibadem/acibadem-hastanesi-istanbul-istmedic-2.jpg",
    "https://www.uzreport.news/fotobank/image/2140c1198ba3f9a2b4c6e12d563da7b1.jpeg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8sEKYyFi1MJBXsrnsMIj9CvFrVX4CO8Y8z76fTLKi-M9OimNumexJ4ikLi9I-U0xf5zI&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSC3nfMiO6kDCx-HlE1San99hwPHQAZFkrhkUHCCwzXQ9vpTitaeW7VllH37tavjjiKfNI&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGxA-RT-rZjCtlj0akA-NdEshH-KjTlVCQgbJ1-ynVRRgNX_S-LL3_3I7KMeHrp-m-otQ&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGABEvfQ9ND757vPMaytxKDBAXURP6pgUq3rTLXUffoaAROZj5XqZEonzR9jyoDGdtP6o&usqp=CAU",
    "https://avatars.mds.yandex.net/get-altay/1595534/2a0000016c7b1a6a8846c51d514f3d857bf5/L_height",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk5PntozB3L4mc-m491P9a-I1cfTTehoZ2-w&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp8M1glJp-OAV21JrUdKmXJj11e7mBmQFWLcYCKzOttyIEbCUyRRaGH4_m3vttX1pIH4w&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxujMGECKZQAa1BlTtVJ5TRnrjtf9wim3CF4A0g1hJj9Sqswz9BA-RYNyTPt9SRSUo6KY&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRivoRzE00UxVjnmbU-KxmxthPL-exvBbf_TQ&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4y_O5yY9Bb1vOm6Otfne3_jurzfyo0msCYA&usqp=CAU",
    "https://www.archdesign.cz/wp-content/uploads/poliklinika-zahradnikova-01.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfVdTkqO7_IPWGki006Hlkq5qKB1mvFBUz6g&usqp=CAU",
    "https://d48-a.sdn.cz/d_48/c_img_gW_t/D7TE40.jpeg?fl=res,600,400,3,ffffff",
    "https://d48-a.sdn.cz/d_48/c_img_H_D/Wo6ZR9.jpeg?fl=res,600,400,3,ffffff",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFozEPtnHpXZqv73lUevaEncbsFQtO6dSOFQ&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRfM06qJJCWZKv4RgyTB_szrlBTY6bnBOCNQrL3LxSr8HbttQ1WyMH0ivJZ1jU8sA0Ho4&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLqA7ncz04SSIF_U28-Lf3XJXOFCCrzo37yg&usqp=CAU",
    "https://www.ordinacepraha.cz/sites/default/files/portal_polikliniky.jpg",
    "https://shttb.uz/shttb/img/bosh.jpg",
    "https://avatars.mds.yandex.net/get-altay/1588111/2a0000016e97e1ac8d561febdce7691e1e1e/L_height",
)

fun initHospitals() {
    for (i in 1..60) {
        val h = Hospital()
        h.name = names.random() + " №$i"
        h.regionId = Api.getRegions().random().id

        val phones = mutableListOf<String>()
        phones.add(generatePhone())
        phones.add(generatePhone())
        phones.add(generatePhone())

        h.phone = phones

        var schedule = schedule1.random()
        schedule += "-"
        schedule += schedule2.random()
        schedule += "   "
        schedule += schedule3.random()
        h.schedule = schedule

        val photo = mutableListOf<String>()
        photo.add(photos.random())
        photo.add(photos.random())
        photo.add(photos.random())
        if (Random(i).nextBoolean()) photo.add(photos.random())
        h.photo = photo
        Api.addHospital(h)
    }
}

fun generatePhone(): String {
    val initials = listOf("90", "91", "97", "93", "71", "33", "88", "99", "94", "97")
    var phone = "+998"
    phone += initials.random()
    for (i in 1..7) {
        phone += Random.Default.nextInt(0, 10).toString()
    }
    return phone
}
