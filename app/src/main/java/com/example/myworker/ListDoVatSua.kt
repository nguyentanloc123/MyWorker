package com.example.myworker

import RecyclerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworker.UserData.DataSlide
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_list_do_vat_sua.*


class ListDoVatSua : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val dataSlider = RecyclerAdapter(
             listOf(
                 DataSlide(
                     1,
                     R.drawable.imgmaygiac,
                     "Máy giặt",
                     "5km",
                     "13"
                 ),
                 DataSlide(
                     2,
                     R.drawable.imgbepga,
                     "Bếp ga",
                     "5km",
                     "13"
                 ),
                 DataSlide(
                     3,
                     R.drawable.imglabo,
                     "Bồn rửa",
                     "5km",
                     "13"
                 ),
                 DataSlide(
                     4,
                     R.drawable.imgmaylanh,
                     "Máy lạnh",
                     "5km",
                     "13"
                 ),
                 DataSlide(
                     5,
                     R.drawable.imgtoilet,
                     "Toilet",
                     "5km",
                     "13"
                 ),
                 DataSlide(
                     6,
                     R.drawable.imgtulanh,
                     "Tủ lạnh",
                     "5km",
                     "13"
                 )
             )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_do_vat_sua)
        linearLayoutManager = LinearLayoutManager(this)
        recyceviewWorker.layoutManager = linearLayoutManager
        recyceviewWorker.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        recyceviewWorker.adapter = dataSlider

    }

}
