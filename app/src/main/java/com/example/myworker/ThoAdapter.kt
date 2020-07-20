package com.example.myworker

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
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.*
import com.example.myworker.UserData.DataLoaiSuaChua
import com.example.myworker.UserData.DataSlide
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData

class ThoAdapter( private val dataSlides: List<WorkerData>) : RecyclerView.Adapter<ThoAdapter.HistoryAdapterViewHolder>(){
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ThoAdapter.HistoryAdapterViewHolder {
        return HistoryAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.list_tho_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return dataSlides.size
    }
    override fun onBindViewHolder(
            holder: ThoAdapter.HistoryAdapterViewHolder,
            position: Int
    ) {
        val context = holder.txtKinhNghiem.context
        holder.bind(dataSlides[position])
        holder.ln1.setOnClickListener {
            val intent = Intent(context,ThoInfoActivity::class.java)
            intent.putExtra("idTho",dataSlides[position].idTho)
            Log.d("thu2",dataSlides[position].idTho)

            Log.d("thu3",dataSlides[position].userName)
            Log.d("thu4",dataSlides[position].toString())


            context.startActivity(intent)
        }


//        holder.tendovatsua.setOnClickListener {
//            val intent = Intent(context,ChonSuaActivity::class.java)
//            intent.putExtra("tenDoVat",dataSlides[position].tenLoaiSuaChua)
//            intent.putExtra("soLan",dataSlides[position].donViTinh)
//            intent.putExtra("giatien",dataSlides[position].giaTien)
//            context.startActivity(intent)
//        }





    }
    inner class HistoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        public val txtnameTho= view.findViewById<TextView>(R.id.txtnameTho)
        public val txtKinhNghiem= view.findViewById<TextView>(R.id.txtKinhNghiem)
        public val ln1= view.findViewById<LinearLayout>(R.id.ln1)
        fun bind(dataSlide: WorkerData)
        {
            txtnameTho.setText(dataSlide.fullname)
            txtKinhNghiem.setText(dataSlide.kinhnghiem)
          //  Log.d("dataloc",dataSlide.tenLoaiSuaChua)

        }
    }


}