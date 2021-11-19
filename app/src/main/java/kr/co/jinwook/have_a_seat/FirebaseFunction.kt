package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kr.co.jinwook.have_a_seat.databinding.ActivityTestBinding

object FirebaseFunction {

    var firebaseIDToken:String? = "ynoAKRBjZvat9nXmFa6aUba2kPU2";

    //카카오,네이버,페이스북,구글,전화번호인증
    lateinit var auth:FirebaseAuth
    val db = Firebase.firestore


    var userData:UserData = UserData()
    var roomInfo:RoomData = RoomData()

    var testValue:Int = 0

    fun test(){

        db.collection("user").document(auth.uid!!)
            .set(userData)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun makeRoomForOrder(roomName:String){
        val docRef = db.collection("rooms").document(roomName)
        docRef
            .set(roomInfo)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }



    }
    fun updateRoomForOrder(roomName:String){
        //트랜잭션써야 데이터 경쟁상태 막을 수 있음
        testValue++
        val ref = db.collection("rooms").document(roomName)


        db.runTransaction { transaction ->
            val snapshot = transaction.get(ref)

            // Note: this could be done without a transaction
            //       by updating the population using FieldValue.increment()
            val newRoomInfo = snapshot.getDouble("test")!! + 1


            transaction.update(ref, "test", newRoomInfo)
            //transaction.update(ref, "test", newRoomInfo)

            // Success
            null
        }.addOnSuccessListener { Log.d(TAG, "Transaction success!") }
            .addOnFailureListener { e -> Log.w(TAG, "Transaction failure.", e) }



       



    }

    fun listenRoomForOrderChange(roomName:String,binding: ActivityTestBinding){
        val docRef = db.collection("rooms").document(roomName)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                roomInfo = snapshot.toObject<RoomData>()!!
                binding.textView.text = roomInfo.test.toString()
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }
    fun addFriend(){


    }
    fun signInAnonymously(activity: Activity){
        auth.signInAnonymously()
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("myTag", "signInAnonymously:success")
                    val user = auth.currentUser
                    userData.name = "Dohyun"
                    userData.age = 4
                    db.collection("user").document(user!!.uid)
                        .set(userData)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }




                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("myTag", "signInAnonymously:failure", task.exception)
                    Toast.makeText(activity.baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

    fun login(){
        db.collection("user").document(auth.uid!!).get().addOnSuccessListener { documentSnapshot ->
            userData = documentSnapshot.toObject<UserData>()!!
            Log.d("myTag", userData.toString())
        }
    }
}