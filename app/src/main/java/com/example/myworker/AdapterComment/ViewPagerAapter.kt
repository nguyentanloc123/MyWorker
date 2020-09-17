package com.example.myworker.AdapterComment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.R
import com.example.myworker.UserData.ImageCoffeSlide

class ViewPagerAapter (private val imgCoffeSlides:List<ImageCoffeSlide>) : RecyclerView.Adapter<ViewPagerAapter.ViewPagerAapterViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAapterViewHolder {
       return ViewPagerAapterViewHolder(LayoutInflater.from(parent.context).inflate(
           R.layout.viewpagerlist,parent,false))
    }

    override fun getItemCount(): Int {
        return imgCoffeSlides.size
    }

    override fun onBindViewHolder(
        holder: ViewPagerAapterViewHolder,
        position: Int
    ) {
        holder.bind(imgCoffeSlides[position])
    }

    inner class ViewPagerAapterViewHolder(view:View) :RecyclerView.ViewHolder(view){
        public val imgcoffe = view.findViewById<ImageView>(R.id.bgcoffe)

        fun bind(imgCoffeSlide: ImageCoffeSlide)
        {
            imgcoffe.setImageResource(imgCoffeSlide.imgCoffe)
        }
    }
}