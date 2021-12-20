package kr.co.jinwook.have_a_seat

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kr.co.jinwook.have_a_seat.databinding.ActivityUploadRestaurantBinding


class UploadRestaurantActivity : AppCompatActivity() {

    val TAG : String = "로그"
    val binding by lazy { ActivityUploadRestaurantBinding.inflate(layoutInflater) }
    lateinit var restaurantName : String
    lateinit var representImageNumber : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = Firebase.firestore

        val restaurants = db.collection("restaurant")

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        binding.btnSelectImage.setOnClickListener {
            selectImage()
            restaurants.whereEqualTo("restaurantName", "부산아지매국밥").get()
                .addOnSuccessListener { documents ->
                    for(document in documents) {
                        Log.d(TAG, "representImage 의 개수는 ${document.getString("representImageNumber")}")
                        representImageNumber = document.getString("representImageNumber").toString()

                    }

                }
        }
        binding.btnUploadImage.setOnClickListener {
            restaurantName = binding.editTextRestaurantName.text.toString()

            uploadImage(restaurantName, representImageNumber)
        }




    }
    //TODO representImageNumberToInt 받았으니 uploadImage 생성자로 넣어 새로 등록한 이미지의 이름을 representImageNumber+1 이 되도록하고 1더한 숫자를 firestore의 데이터에 셋
    private fun uploadImage(restaurantName:String, representImageNumber:String) {
        var changedRepresentImageNumber = representImageNumber.toInt() + 1
        var ImageNumber = (changedRepresentImageNumber).toString()

        var progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File")
        progressDialog.setCancelable(false)
        progressDialog.show()


        val storageReference = FirebaseStorage.getInstance().getReference("restaurant/$restaurantName/$ImageNumber/")
        storageReference.putFile(imageUri)
            .addOnSuccessListener {
                binding.imageUploadRestaurant.setImageURI(null)
                Toast.makeText(this@UploadRestaurantActivity, "Successfully Uploaded", Toast.LENGTH_LONG).show()
                if(progressDialog.isShowing) progressDialog.dismiss()
            }.addOnFailureListener{
                Toast.makeText(this@UploadRestaurantActivity, "Failed", Toast.LENGTH_LONG).show()
                if(progressDialog.isShowing) progressDialog.dismiss()
            }

        Firebase.firestore.collection("restaurant").document("부산아지매").update("representImageNumber",ImageNumber)
    }

    private fun selectImage() {
        val intentSelectImage = Intent()
        intentSelectImage.type = "image/*"
        intentSelectImage.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intentSelectImage, 100)
    }

    lateinit var imageUri : Uri

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            imageUri = data?.data!!
            binding.imageUploadRestaurant.setImageURI(imageUri)
            Log.d(TAG, "PageForUploadActivity - onActivityResult() called")
        }

    }

}