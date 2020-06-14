import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.*
import com.example.myworker.UserData.DataSlide

class RecyclerAdapter( private val dataSlides: List<DataSlide>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.RecyclerAdapterViewHolder {
        return RecyclerAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemrecyceview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSlides.size
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapter.RecyclerAdapterViewHolder,
        position: Int
    ) {
        val context = holder.txtsotho.context
        holder.bind(dataSlides[position])
        holder.btnAdd.setOnClickListener {
            val tenDoDung = dataSlides[position].nameDoVat
            var idDoVat = dataSlides[position].id
            val intent = Intent(context, AddDoVatSua::class.java)
            intent.putExtra("tendovat",tenDoDung)
            intent.putExtra("idDoVat",idDoVat.toString())
            context.startActivity(intent)
        }
        holder.btnMap.setOnClickListener {
            context.startActivity(Intent(context, MainMap::class.java))
        }
    }
    inner class RecyclerAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        public val imgDoDung = view.findViewById<ImageView>(R.id.imgDoDUng)
        public val txtnamedovat= view.findViewById<TextView>(R.id.txtnamedovat)
        public val txtsokilomet= view.findViewById<TextView>(R.id.txtsokilomet)
        public val txtsotho= view.findViewById<TextView>(R.id.txtsotho)
        public val btnAdd= view.findViewById<Button>(R.id.btnAdd)
        public val btnMap= view.findViewById<Button>(R.id.btnMap)


        fun bind(dataSlide: DataSlide)
        {
            txtnamedovat.setText(dataSlide.nameDoVat)
            imgDoDung.setImageResource(dataSlide.imgDoVat)
            txtsokilomet.setText(dataSlide.khoangCach)
            txtsotho.setText(dataSlide.soThoSuaHienCo)
        }
    }
}