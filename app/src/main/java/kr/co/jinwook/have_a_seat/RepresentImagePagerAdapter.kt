package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import kr.co.jinwook.have_a_seat.databinding.RepresentImageItemViewpagerBinding
import java.io.File

class RepresentImagePagerAdapter : RecyclerView.Adapter<Holder>() {
    val TAG : String = "로그"
    lateinit var activity : Activity
    lateinit var restaurantName : String
    var imageNumberList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Log.d(TAG, "RepresentImagePagerAdapter - onCreateViewHolder() called")
        val binding = RepresentImageItemViewpagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.d(TAG, "RepresentImagePagerAdapter - onBindViewHolder() called")
        val imageNumber = imageNumberList[position]

        holder.setImage(activity,restaurantName,imageNumber)
    }

    override fun getItemCount(): Int {

        return imageNumberList.size
    }
}

class Holder(val binding:RepresentImageItemViewpagerBinding) : RecyclerView.ViewHolder(binding.root) {
    val TAG : String = "로그"


    fun setImage(activity : Activity, restaurantName:String, imageNumber: String) {
        //Log.d("holderTag", "Holder - setImage() called")
        val progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Fetching Image...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        UIFunction.storageRef.child("restaurant/$restaurantName/$imageNumber").getBytes(UIFunction.ONE_MEGABYTE)

            .addOnSuccessListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                var image: Bitmap? = null
                image = BitmapFactory.decodeByteArray(it,0,it.size)
                binding.imageViewRepresentImage.setImageBitmap(image)
                Log.d("myTag","이미지 다운 성공")
                Log.d("myTag",it.size.toString()) }
            .addOnFailureListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                Log.d("myTag",it.toString())
            }
        /*
        val storageRef = FirebaseStorage.getInstance().reference.child("restaurant/$restaurantName/$imageNumber/")

        val localFile = File.createTempFile("tempImage", "jpg")

        storageRef.getFile(localFile).addOnSuccessListener {

            if (progressDialog.isShowing) progressDialog.dismiss()

            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.imageViewRepresentImage.setImageBitmap(bitmap)
        }.addOnFailureListener{

            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(activity, "Failed to download image", Toast.LENGTH_LONG).show()
        }
*/
    }

}