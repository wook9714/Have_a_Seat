package kr.co.jinwook.have_a_seat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.jinwook.have_a_seat.databinding.OrderSheetItemRecyclerBinding

class OrderSheetRecyclerAdapter: RecyclerView.Adapter<OrderSheetHolder>() {
    var listData = mutableListOf<OrderSheetItemForm>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSheetHolder {
        val binding = OrderSheetItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderSheetHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderSheetHolder, position: Int) {
        val data = listData.get(position)
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}

class OrderSheetHolder(val binding:OrderSheetItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
    fun setData(data:OrderSheetItemForm){
        binding.payState.text = data.payState
        binding.shopName.text = data.shopName
        binding.orderDescription.text = data.orderDescription
        binding.orderPrice.text = "${data.price}원"

        UIFunction.downloadAndSetImageViewFromFB(binding.shopImage,"images/test.jpg")






    }
}