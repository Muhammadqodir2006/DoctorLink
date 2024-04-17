package uni.dev.doctorlink.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uni.dev.doctorlink.model.Booking
import uni.dev.doctorlink.model.Comment
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.model.Specialty
import uni.dev.doctorlink.model.User
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class Api private constructor() {
    companion object {
        private const val TIMELIMITSECONDS = 120

        private val reference = FirebaseDatabase.getInstance().reference
        private val auth = FirebaseAuth.getInstance()

        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        private val regions = listOf(
            Region(0, "Toshkent shahar"),
            Region(1, "Qoraqalpog'iston Respublikasi"),
            Region(2, "Toshkent viloyati"),
            Region(3, "Farg'ona viloyati"),
            Region(4, "Andijon viloyati"),
            Region(5, "Namangan viloyati"),
            Region(6, "Sirdaryo viloyati"),
            Region(7, "Xorazm viloyati"),
            Region(8, "Jizzax viloyati"),
            Region(9, "Samarqand viloyati"),
            Region(10, "Buxoro viloyati"),
            Region(11, "Surxondaryo viloyati"),
            Region(12, "Qashqadaryo viloyati"),
            Region(13, "Navoiy viloyati")
        )

        fun getRegions(): List<Region> {
            return regions
        }

        fun getRegion(id: Int): Region {
            for (i in regions) if (i.id == id) return i
            return Region(-1, "")
        }

        private val specialties = listOf(
            Specialty(0, "Allergolog"),
            Specialty(1, "Venerolog"),
            Specialty(2, "Virusolog"),
            Specialty(3, "Ginekolog"),
            Specialty(4, "Dermatolog"),
            Specialty(5, "Dietolog"),
            Specialty(6, "Kardiolog"),
            Specialty(7, "Nevropatolog"),
            Specialty(8, "Nefrolog"),
            Specialty(9, "Oftalmolog"),
            Specialty(10, "Onkolog"),
            Specialty(11, "Ortoped"),
            Specialty(12, "Pediatr"),
            Specialty(13, "Psixiatr"),
            Specialty(14, "Psixoterapevt"),
            Specialty(15, "Stomatolog"),
            Specialty(16, "Terapevt"),
            Specialty(17, "Travmatolog"),
            Specialty(18, "Urolog"),
            Specialty(19, "Xirurg"),
            Specialty(20, "Endokrinolog"),
        )

        fun getSpecialties(): List<Specialty> {
            return specialties
        }

        private val appointmentTimes = listOf(
            "08:00",
            "08:30",
            "09:00",
            "09:30",
            "10:00",
            "10:30",
            "11:00",
            "11:30",
            "12:00",
            "12:30",
            "14:00",
            "14:30",
            "15:00",
            "15:30",
            "16:00",
            "16:30",
            "17:00",
            "17:30",
        )

        fun getAppointmentTimes(): List<String> {
            return appointmentTimes
        }

        private val daysOfWeek = listOf("Dushanba", "Seshanba", "Chorshanba", "Payshanba", "Juma", "Shanba", "Yakshanba")

        fun getDay(num: Int): String {
            return daysOfWeek[num - 1]
        }

        private val months = listOf(
            "Yanvar", "Fevral", "Mart", "Aprel", "May", "Iyun", "Iyul", "Avgust", "Sentyabr", "Oktyabr", "Noyabr", "Dekabr"
        )

        fun getMonth(num: Int): String {
            return months[num - 1]
        }

        fun getSpecialty(specialtyId: Int): Specialty {
            for (i in specialties) {
                if (i.id == specialtyId) return i
            }
            return Specialty(-1, "")
        }


        private val users = reference.child("users")
        private val doctors = reference.child("doctors")
        private val hospitals = reference.child("hospitals")

        private val bookings = reference.child("bookings")

        private const val TAG = "TAG"
        private const val COMMENTS = "comments"
        private const val SAVED_DOCTORS = "savedDoctors"
        private const val SAVED_HOSPITALS = "savedHospitals"

        fun hasInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

            val hasConnection = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            if (hasConnection != null) Log.d(TAG, "hasInternet: $hasConnection")
            return if (activeNetwork != null) hasConnection ?: false
            else false
        }

        fun sendCode(
            phone: String, context: Context, onFail: () -> Unit, onSent: (verificationId: String) -> Unit
        ) {
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
                override fun onVerificationFailed(e: FirebaseException) {
                    Log.d("TAG", "onVerificationFailed: ${e.message}")
                    onFail()
                }

                override fun onCodeSent(
                    verificationId: String, token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("TAG", "onCodeSent: success")
                    onSent(verificationId)
                }
            }

            val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phone).setTimeout(TIMELIMITSECONDS.toLong(), TimeUnit.SECONDS)
                .setCallbacks(callbacks).setActivity(context as Activity).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        fun checkCode(
            verificationId: String, smsCode: String, result: (isSuccessful: Boolean) -> Unit
        ) {
            Log.d("TAG", "checkCode: sms = $smsCode")
            val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "checkCode is success:  ${task.isSuccessful}")
                    result(task.isSuccessful)
                } else {
                    Log.d("TAG", "checkCode exception: ${task.exception}")
                    result(false)
                }

            }
        }

        // ---------------------------------------------------------------------------------------

        fun register(user: User, onRegisterSuccess: (user: User) -> Unit) {
            val key = users.push().key.toString()
            user.key = key
            users.child(key).setValue(user)
            Log.d("TAG", "register: success")
            onRegisterSuccess(user)
        }

        fun getUser(phone: String, callback: (User?) -> Unit) {
            users.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val users = snapshot.children
                    users.forEach {
                        val user = it.getValue(User::class.java)!!
                        if (user.phone == phone) callback(user)
                        Log.d("TAG", "getUser: $user")
                    }
//                    Log.d("TAG", "getUser: null")
                    callback(null)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getUser: $${error.message}")
                }
            })
        }

        fun getUserByKey(userKey: String, callback: (User) -> Unit) {
            users.child(userKey).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)!!
                    callback(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getUserByKey: $error")
                }
            })
        }

        fun updateUser(user: User, callback: () -> Unit) {
            users.child(user.key!!).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.child("birthyear").ref.setValue(user.birthyear!!)
                    snapshot.child("name").ref.setValue(user.name!!)
                    snapshot.child("phone").ref.setValue(user.phone!!)
                    snapshot.child("surname").ref.setValue(user.surname!!)
                    Log.d(TAG, "updateUser: ${snapshot.child("birthyear")}")
                    callback()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "updateUser: $error")
                }

            })

        }

        fun savedDoctorUpdate(userKey: String, doctorKey: String, callback: (Boolean) -> Unit) {
            val savedDoctors = users.child(userKey).child(SAVED_DOCTORS)
            savedDoctors.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    for (i in data) {
                        val a = i.getValue(String::class.java)
                        if (a == doctorKey) {
                            snapshot.child(i.key!!).ref.removeValue()
                            callback(false)
                            return
                        }
                    }
                    savedDoctors.push().setValue(doctorKey)
                    callback(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "saveDoctorUpdate: $error ")
                }

            })
        }

        fun savedHospitalUpdate(userKey: String, hospitalKey: String, callback: (Boolean) -> Unit) {
            val savedHospitals = users.child(userKey).child(SAVED_HOSPITALS)
            savedHospitals.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    for (i in data) {
                        val a = i.getValue(String::class.java)
                        if (a == hospitalKey) {
                            snapshot.child(i.key!!).ref.removeValue()
                            callback(false)
                            return
                        }
                    }
                    savedHospitals.push().setValue(hospitalKey)
                    callback(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "saveDoctorUpdate: $error ")
                }

            })
        }

        private fun checkSaved(
            userKey: String, key: String, refKey: String, callback: (Boolean) -> Unit
        ) {
            val saved = users.child(userKey).child(refKey)
            saved.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    data.forEach {
                        if (it.getValue(String::class.java)!! == key) {
                            callback(true)
                            return
                        }
                        callback(snapshot.hasChild(key))
                    }
                    callback(false)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "isSaved: $error")
                }

            })
        }

        fun isSaved(userKey: String, key: String, isDoctor: Boolean, callback: (Boolean) -> Unit) {
            val refKey = if (isDoctor) SAVED_DOCTORS else SAVED_HOSPITALS
            checkSaved(userKey, key, refKey) {
                callback(it)
            }
        }

        fun getSavedKeys(userKey: String, isDoctor: Boolean, callback: (List<String>) -> Unit) {
            val refKey = if (isDoctor) SAVED_DOCTORS else SAVED_HOSPITALS
            savedKeys(userKey, refKey) {
                callback(it)
            }
        }

        private fun savedKeys(userKey: String, refKey: String, callback: (List<String>) -> Unit) {
            val savedDoctorIds = users.child(userKey).child(refKey)
            savedDoctorIds.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    val savedKeyS = mutableListOf<String>()
                    data.forEach {
                        val savedItemKey = it.getValue(String::class.java)!!
                        savedKeyS.add(savedItemKey)
                    }
                    Log.d(TAG, "getSavedKeys: $savedKeyS")
                    callback(savedKeyS)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getSavedKeys: $error")
                }
            })
        }

        // ---------------------------------------------------------------------------------------

        fun addDoctor(doctor: Doctor) {
            val key = doctors.push().key!!
            doctor.key = key
            doctors.child(key).setValue(doctor)
        }

        fun getDoctor(key: String, callback: (Doctor) -> Unit) {
            doctors.child(key).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val doctor = snapshot.getValue(Doctor::class.java)!!
                    callback(doctor)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getDoctor: $error")
                }

            })
        }

        fun updateDoctor() {
            // If clinic changes so does the region
        }

        fun getAllDoctors(callback: (List<Doctor>) -> Unit) {
            doctors.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children
                    var doctors = mutableListOf<Doctor>()
                    list.forEach {
                        val doctor = it.getValue(Doctor::class.java)!!
                        doctors.add(doctor)
                    }
//                    Log.d("TAG", "getAllDoctors: ${doctors.size}")
                    doctors = doctors.sortedBy {
                        it.rating
                    }.reversed().toMutableList()
                    callback(doctors)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "getDoctors exception: $${error.message}")
                }
            })
        }

        fun addComment(comment: Comment) {
            val comments = doctors.child(comment.doctorKey!!).child(COMMENTS)
            val key = comments.push().key!!
            comment.key = key
            comments.child(key).setValue(comment)
            updateRating(comment.doctorKey)
        }

        private fun updateRating(doctorKey: String) {
            doctors.child(doctorKey).child(COMMENTS).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var sum = 0
                    var count = 0
                    val data = snapshot.children
                    data.forEach {
                        val comment = it.getValue(Comment::class.java)!!
                        sum += comment.rating!!
                        count += 1
                    }
                    val mean = sum.toFloat() / count
                    doctors.child(doctorKey).child("rating").setValue(mean)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        fun deleteAllComments() {
            doctors.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    data.forEach {
                        doctors.child(it.key!!).child("comments").removeValue()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        fun getComments(doctorKey: String, callback: (List<Comment>) -> Unit) {
            val com = doctors.child(doctorKey).child(COMMENTS)
            com.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    val comments = mutableListOf<Comment>()
                    data.forEach {
                        val comment = it.getValue(Comment::class.java)!!
                        comments.add(comment)
                    }
                    Log.d(TAG, "getComments: $comments")
                    callback(comments)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "getComments: $error")
                }
            })
        }

        // ---------------------------------------------------------------------------------------


        fun addBooking(
            doctorKey: String, userKey: String, date: LocalDate, time: String, callback: () -> Unit
        ) {
            val key = bookings.push().key!!
            val dateFormatted = formatDate(date)
            val booking = Booking(
                key = key, userKey = userKey, doctorKey = doctorKey, date = dateFormatted, time = time
            )
            bookings.child(key).setValue(booking)
            callback()
        }

        fun deleteBooking(bookingKey: String, callback: () -> Unit) {
            bookings.child(bookingKey).removeValue()
            callback()
        }

        private fun getTimeActive(t: String): Boolean {
            val time = LocalTime.parse(t, timeFormatter)
            val now = LocalTime.now()
            return time.isAfter(now)
        }

        private fun formatDate(date: LocalDate): String {
            return dateFormatter.format(date)
        }

        fun getBookingsOfUser(userKey: String, active: Boolean, callback: (List<Booking>) -> Unit) {
            getAllBookingsOfUser(userKey) { bookingsAll ->
                val list = mutableListOf<Booking>()
                bookingsAll.forEach {
                    val date = LocalDate.parse(it.date!!, dateFormatter)
                    val today = LocalDate.now()
                    val isActive = if (date.isEqual(today)) getTimeActive(it.time!!)
                    else date.isAfter(today)
                    if (active == isActive) list.add(it)
                }
                callback(list)
            }
        }

        private fun getAllBookingsOfUser(userKey: String, callback: (List<Booking>) -> Unit) {
            bookings.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    val bookings = mutableListOf<Booking>()
                    data.forEach {
                        val booking = it.getValue(Booking::class.java)!!
                        if (booking.userKey == userKey) bookings.add(booking)
                    }
                    callback(bookings)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getAllBookingsOfUser: $error")
                }
            })
        }

        private fun getAllBookingsOfDoctor(doctorKey: String, callback: (List<Booking>) -> Unit) {
            bookings.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    val bookings = mutableListOf<Booking>()
                    data.forEach {
                        val booking = it.getValue(Booking::class.java)!!
                        if (booking.doctorKey == doctorKey) bookings.add(booking)
                    }
                    callback(bookings)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "getAllBookingsOfDoctor: $error")
                }
            })
        }


        // ---------------------------------------------------------------------------------------

        fun addHospital(hospital: Hospital) {
            val key = hospitals.push().key!!
            hospital.key = key
            hospitals.child(key).setValue(hospital)
        }

        fun getHospital(key: String, callback: (Hospital) -> Unit) {
            getAllHospitals { allHospitals ->
                allHospitals.forEach {
                    if (it.key == key) {
                        callback(it)
//                        Log.d(TAG, "getHospital: ")
                    }
                }
            }
        }


        fun getDoctorsOfHospital(hospitalKey: String, callback: (List<Doctor>) -> Unit) {
            getAllDoctors { allDoctors ->
                val doctors = mutableListOf<Doctor>()
                allDoctors.forEach {
                    if (it.hospitalKey == hospitalKey) doctors.add(it)
                }
                callback(doctors)
            }
        }

        fun getAllHospitals(callback: (List<Hospital>) -> Unit) {
            hospitals.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children
                    val hospitals = mutableListOf<Hospital>()
                    data.forEach {
                        val hospital = it.getValue(Hospital::class.java)!!
                        hospitals.add(hospital)
                    }
//                    Log.d(TAG, "getAllHospitals: ${hospitals.size}")
                    callback(hospitals)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "getAllHospitals: $error")
                }
            })
        }

        fun getBookedTimes(doctorKey: String, date: LocalDate, callback: (List<String>) -> Unit) {
            getAllBookingsOfDoctor(doctorKey) { list ->
                val unavailableTimes = mutableListOf<String>()
                val dateFormatted = formatDate(date)
                list.forEach { if (dateFormatted == it.date) unavailableTimes.add(it.time!!) }
                callback(unavailableTimes)
            }
        }

        fun getUncommentedBookings(userKey: String, callback: (List<Booking>) -> Unit) {
            getBookingsOfUser(userKey, false) { bookings ->
                val list = mutableListOf<Booking>()
                bookings.forEach { if (!it.commented!!) list.add(it) }
                callback(list)
            }
        }

        fun makeCommented(bookingKey: String, callback: () -> Unit) {
            bookings.child(bookingKey).child("commented").setValue(true)
            callback()
        }

    }
}