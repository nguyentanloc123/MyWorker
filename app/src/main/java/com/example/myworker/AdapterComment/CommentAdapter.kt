package com.example.myworker.AdapterComment

import android.content.Context
import android.content.Intent
import android.media.Image
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.*
import com.example.myworker.UserData.DataComment
import com.example.myworker.UserData.DataLoaiSuaChua
import com.example.myworker.UserData.DataSlide
import com.example.myworker.UserData.UserWorkAdd

class CommentAdapter( private val dataSlides: List<DataComment>) : RecyclerView.Adapter<CommentAdapter.HistoryAdapterViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.HistoryAdapterViewHolder {
        return HistoryAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSlides.size
    }
    override fun onBindViewHolder(
        holder: CommentAdapter.HistoryAdapterViewHolder,
        position: Int
    ) {
        val context = holder.txtDaytime.context
        holder.bind(dataSlides[position])
//        holder.tendovatsua.setOnClickListener {
//            val intent = Intent(context,ChonSuaActivity::class.java)
//            intent.putExtra("tenDoVat",dataSlides[position].tenLoaiSuaChua)
//            intent.putExtra("soLan",dataSlides[position].donViTinh)
//            intent.putExtra("giatien",dataSlides[position].giaTien)
//            context.startActivity(intent)
//        }

    }
    inner class HistoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        public val txtTenNguoiDung= view.findViewById<TextView>(R.id.textView40)
        public val txtBinhLuan= view.findViewById<TextView>(R.id.txtBinhLuan)
        public val txtDaytime= view.findViewById<TextView>(R.id.textView41)
        fun bind(dataSlide: DataComment)
        {
           txtTenNguoiDung.setText(dataSlide.userName+ "Đã bình luận")
            txtBinhLuan.setText(dataSlide.textComment)
            txtDaytime.setText(dataSlide.dateTime)
        }
    }


}