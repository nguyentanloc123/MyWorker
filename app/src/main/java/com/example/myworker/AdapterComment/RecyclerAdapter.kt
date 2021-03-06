import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.*
import com.example.myworker.Screen.Fix.ChonSuaActivity
import com.example.myworker.UserData.DataLoaiSuaChua

class RecyclerAdapter( private val dataSlides: List<DataLoaiSuaChua>) : RecyclerView.Adapter<RecyclerAdapter.HistoryAdapterViewHolder>(){
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): RecyclerAdapter.HistoryAdapterViewHolder {
        return HistoryAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.itemlistdovat,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return dataSlides.size
    }
    override fun onBindViewHolder(
            holder: RecyclerAdapter.HistoryAdapterViewHolder,
            position: Int
    ) {
        val context = holder.tendovatsua.context
        holder.bind(dataSlides[position])
        holder.tendovatsua.setOnClickListener {
            val intent = Intent(context,
                ChonSuaActivity::class.java)
            intent.putExtra("tenDoVat",dataSlides[position].tenLoaiSuaChua)
            intent.putExtra("soLan",dataSlides[position].donViTinh)
            intent.putExtra("giatien",dataSlides[position].giaTien)
           context.startActivity(intent)
        }





    }
    inner class HistoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        public val tendovatsua= view.findViewById<TextView>(R.id.tendovatsua)
        public val solan= view.findViewById<TextView>(R.id.solan)
        fun bind(dataSlide: DataLoaiSuaChua)
        {
            tendovatsua.setText(dataSlide.tenLoaiSuaChua)
            solan.setText(dataSlide.donViTinh)
            Log.d("dataloc",dataSlide.tenLoaiSuaChua)

        }
    }


}