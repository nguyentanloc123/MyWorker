package com.example.myworker.UserData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.R

class HistoryAdapter( private val dataSlides: List<UserWorkAdd>) : RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryAdapterViewHolder {
        return HistoryAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSlides.size
    }

    override fun onBindViewHolder(
        holder: HistoryAdapter.HistoryAdapterViewHolder,
        position: Int
    ) {
        val context = holder.txtTenSanPham.context
        holder.bind(dataSlides[position])
        // check hinh anh cua vat dung
        if(dataSlides[position].idDovat.equals("1"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imgmaygiac)
        }
        if(dataSlides[position].idDovat.equals("2"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imgbepga)
        }
        if(dataSlides[position].idDovat.equals("3"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imglabo)
        }
        if(dataSlides[position].idDovat.equals("4"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imgmaylanh)
        }
        if(dataSlides[position].idDovat.equals("5"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imgtoilet)
        }
        if(dataSlides[position].idDovat.equals("6"))
        {
            holder.bgSanPham.setImageResource(R.drawable.imgtulanh)
        }





        if(dataSlides[position].flagCheck == false)
        {
            holder.txtTinhTrangSua.setText("Chưa sửa")
            holder.txtTinhTrangSua.setTextColor("#E23434".toColorInt())
        }
        else if(dataSlides[position].flagCheck == true)
        {
            holder.txtTinhTrangSua.setText("Đã sửa")
            holder.txtTinhTrangSua.setTextColor("#34E29F".toColorInt())
        }



    }
    inner class HistoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        public val txtTenSanPham= view.findViewById<TextView>(R.id.txtTenSanPham)
        public val txtNgayThang= view.findViewById<TextView>(R.id.txtNgayThang)
        public val txtTinhTrangSua= view.findViewById<TextView>(R.id.txtTinhTrangSua)
        public val bgSanPham= view.findViewById<ImageView>(R.id.bgSanPham)



        fun bind(dataSlide: UserWorkAdd)
        {
           // txtTenSanPham.setText(dataSlide.dovatcansua)
            txtNgayThang.setText(dataSlide.dateDanng)
        //   Log.d("dataloc",dataSlide.dovatcansua)

        }
    }


}