package uni.dev.doctorlink.initData

import android.util.Log
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.util.Api
import kotlin.random.Random

val namesMale = listOf(
    "Muhammad", "Mustafo", "Imron", "Ali", "Umar", "Azizbek", "Sardor", "Ibrohim", "Abdulloh", "Asadbek", "Islom", "Kamron", "Aziz", "Otabek", "Javohir", "Jahongir", "Muhammadali", "Alisher", "Firdavs", "Sanjar", "Bilol", "Sarvar", "Jamshid", "Akbar", "Samandar", "Yusuf", "Amir", "Sherzod", "Behruz", "Dilshod"
)
val namesFemale = listOf(
    "Madina", "Mubina", "Saida", "Hadicha", "Muslima", "Maftuna", "Soliha", "Yasmina", "Sevinch", "Aziza", "Marjona", "Sabina", "Farangiz", "Shahnoza", "Munisa", "Malika", "Durdona", "Nilufar", "Jasmina", "Mushtariy", "Dilnur", "Muxlisa", "Robiya", "Charos"
)
val surnames = listOf(
    "Aliyev", "Azizov", "Hakimov", "Amirov", "Malikov", "Komilov", "Karimov", "Temurov", "Abdullayev", "Bahromov", "Salimov", "Hojiyev", "Tojiyev", "Ikromov", "Jamolov", "Jalilov", "Umarov", "Valiyev", "Shokirov", "Yusupov", "Latipov"
)
val prices = listOf(
    70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000, 170000, 180000, 190000, 200000
)
val info = "Germaniya va Hindistonda malaka oshirgan tajribali shifokor.\n\n Ish jadvali : Du-Ju 9:00-16:00"
val yearStarted = listOf(
    2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
)
val imagesMale = listOf(
    "https://media.istockphoto.com/id/177373093/photo/indian-male-doctor.jpg?s=612x612&w=0&k=20&c=5FkfKdCYERkAg65cQtdqeO_D0JMv6vrEdPw3mX1Lkfg=",
    "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?size=626&ext=jpg&ga=GA1.1.735520172.1710547200&semt=ais",
    "https://i.pinimg.com/736x/f4/c9/ef/f4c9ef33d04a22050038e9e53eeb7d85.jpg",
    "https://static.vecteezy.com/system/resources/thumbnails/028/287/384/small_2x/a-mature-indian-male-doctor-on-a-white-background-ai-generated-photo.jpg",
    "https://previews.123rf.com/images/dolgachov/dolgachov1307/dolgachov130700228/20605818-bright-picture-of-male-doctor-with-stethoscope.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGgGPa4IUXTgNv6Ibb6W7dmjK6l7ssD0oFsHARB0Wm5FXOdqt6KAKeuolxyie5YsZR80g&usqp=CAU",
    "https://us.images.westend61.de/0001529325pw/handsome-male-doctor-against-gray-wall-in-hospital-GIOF11522.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRagG91uolw3ZKKNYmAL5179mwg096tqNVsjneZdyxvbPURmyYiqaooWYoB3o7kn3xxDUs&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNACGlvc9R8OWY219jFaTg278GwDNqBGMfBA&usqp=CAU",
    "https://img.freepik.com/free-photo/doctor-standing-with-folder-stethoscope_1291-16.jpg",
    "https://img.freepik.com/free-photo/portrait-smiling-attractive-male-doctor-man_171337-5066.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS11UKPRURUYRe6GDYToBFcfirrP1eAn9QeB--oO3YQKhXQPry3P-LK-EL3E1tZzVufBcM&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3kT1ZMs29yLQZo7gd7Y7CsQCBYwXb-epr_S6jHxVaB-6TtU266WPy2yUnp41KP3s_HrY&usqp=CAU",
    "https://img.freepik.com/free-photo/handsome-bearded-guy-posing-against-white-wall_273609-20597.jpg?size=626&ext=jpg&ga=GA1.1.735520172.1710720000&semt=sph",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpIoI76wjvQ2pq6t25TLmRAwTxv118OFKZxykoCiY_fWRtR8QRr1nWWcDtG3tHdgCuWu8&usqp=CAU",
    "https://static.vecteezy.com/system/resources/thumbnails/005/346/410/small_2x/close-up-portrait-of-smiling-handsome-young-caucasian-man-face-looking-at-camera-on-isolated-light-gray-studio-background-photo.jpg",
    "https://t3.ftcdn.net/jpg/02/58/89/90/360_F_258899001_68CalsKTRk6PZQgWH9JhR4heBlncCko9.jpg",
    "https://t3.ftcdn.net/jpg/03/16/72/68/360_F_316726850_Kp5gHry52XIA0Cedl7b2K1remR1hJ8NU.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH2MM5MS2lxpNqz5fqqxz-tumCvr7SXuRSKX4abuRyRuP45mg70kkJmq8-VxACXQL_Fu8&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxE9cEI1cuPKqWyydPiHHDi4ST3gcb0q5veA&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0_6JHPXCJFdHElYHMcKnvb6pK6npKcBDqjAxvTX7z_d-04SCQ2TdFbgiS5m4a3novXlg&usqp=CAU",
    "https://cdn.shopify.com/s/files/1/0503/7857/1962/products/ultra-xy-yd1086-2-m-3_2400x1200_crop_center.jpg?v=1670488656",
    "https://img.freepik.com/free-photo/handsome-confident-smiling-man-with-hands-crossed-chest_176420-18743.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPT9mqiidTshsbvsr8SDUYnTLNv3f31RTL0JrQFOZd7ECP8OQ1h0mR_Ze3VlzMPknrUhs&usqp=CAU",
    "https://www.pngall.com/wp-content/uploads/8/Young-Man-PNG-Image.png",
)
val imagesFemale = listOf(
    "https://i.pinimg.com/736x/b9/97/a5/b997a530822d0f2c03259070d4590d45.jpg",
    "https://i.pinimg.com/736x/1b/52/fd/1b52fd81c2282b432b85dc6a8a01f13d.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHSm9TYgjTT2YKo7tVK_1qfVVr2keNnWuJRbpOHix8OD5TPsuM0lZ13SfZnlPxfPkYz_o&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzuySE_q4w-YgGPhIs9x8pS-jb091_oGKqVtUTkFwWlwt9sATrfSL67QgUScHWxHVZ6OQ&usqp=CAU",
    "https://img.freepik.com/free-photo/pleased-young-female-doctor-wearing-medical-robe-stethoscope-around-neck-standing-with-closed-posture_409827-254.jpg",
    "https://img.freepik.com/free-photo/female-doctor-hospital-with-stethoscope_23-2148827776.jpg",
    "https://d25iein5sonfaj.cloudfront.net/v1/public-drupal-medmastery-assets-production/migrated-images/female%20doctor.jpg?cfg=eyJ3aWR0aCI6MTIwMCwiaGVpZ2h0Ijo2MzAsIm91dHB1dCI6IndlYnAifQ==",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1LViHVlh5IME3nbqjeqq3y8DTHi2UqLniFcsRa_QNfvdzH0EucPMHPKVGRc5J6Qx84Jo&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZDKEjGacIC_oP1ovqeDjvhdSxstaCmhh8iw&usqp=CAU",
    "https://media.istockphoto.com/id/1292015214/photo/portrait-female-doctor-stock-photo.jpg?s=612x612&w=0&k=20&c=nr4XaWnRPQnWqwhzahajZhskIDG1yK9DmIteV5gpUOI=",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKMCPOifkQwBQuaGNwIrbJjGuzDCAW0-244rNrd3atI7VQxujR9vy6b9rRw-Eey8rmoRE&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSENgJb6pXryZ6RPclpjN6oXE6YZdvZUUVZmI7wf7aYrtngnraSrCXHMXRQBj0dfIuCpRM&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_yG7DcoZA1j2dGHBV02WvH6pB-zaTdjw7ykh0_OgL9GVFv_J-tE0ne7OKU8Bg5nJgARY&usqp=CAU",
    "https://static01.nyt.com/images/2016/07/12/science/12SALARIES/12SALARIES-articleLarge.jpg?quality=75&auto=webp&disable=upscale",
)
// hospitalKey, regionId


fun initDoctors() {
    Api.getAllHospitals {
        var m    = 0
        for (i in 1..400) {
            val d = Doctor()
            val gender = Random(i).nextInt(30) > 10
            if (gender) m++
            d.name = if (gender) namesMale.random() else namesFemale.random()
            d.surname = surnames.random()
            if (!gender) d.surname += "a"
            d.image = if (gender) imagesMale.random() else imagesFemale.random()
            d.gender = gender
            d.rating = 0f
            d.price = prices.random()
            d.yearStarted = yearStarted.random()
            d.info = info

            val specialties = mutableListOf<Int>()
            val random = Random.Default
            specialties.add(random.nextInt(Api.getSpecialties().size))
            if (random.nextBoolean()) {
                val specialtyId = random.nextInt(Api.getSpecialties().size)
                specialties.add(specialtyId)
            }
            d.specialty = specialties
            val clinic = it.random()

            d.hospitalKey = clinic.key
            d.regionId = clinic.regionId

            Api.addDoctor(d)
        }
//        Log.d("TAG", "initDoctors: $m")
    }
}