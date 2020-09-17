package com.example.myworker.AdapterComment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.R
import com.example.myworker.Screen.Find.ThoInfoActivity
import com.example.myworker.UserData.WorkerData

class ThoAdapter( private val dataSlides: List<WorkerData>) : RecyclerView.Adapter<ThoAdapter.HistoryAdapterViewHolder>(){
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): HistoryAdapterViewHolder {
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
        holder: HistoryAdapterViewHolder,
        position: Int
    ) {
        val context = holder.txtKinhNghiem.context
        holder.bind(dataSlides[position])
        holder.ln1.setOnClickListener {
            val intent = Intent(context,
                ThoInfoActivity::class.java)
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