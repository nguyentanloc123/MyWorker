package com.example.myworker.FragmentContanier

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.myworker.BarnCodeActivity
import com.example.myworker.R
import com.example.myworker.UserData.ImageCoffeSlide
import com.example.myworker.ViewPagerAapter


class FragmentAccount : Fragment() {
    val imgcoffe = ViewPagerAapter(
        listOf(
            ImageCoffeSlide(
                R.drawable.coffehouseimg
            ),
            ImageCoffeSlide(
                R.drawable.phuclongimg
            ),
            ImageCoffeSlide(
                R.drawable.starkbuckimg
            )
        )

    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.accountfragment, container, false)

        val buttonMenu = rootView.findViewById<Button>(R.id.btnPopUp)
        val textviewTien =rootView.findViewById<TextView>(R.id.textviewTien)
        val cardViewDoAn = rootView.findViewById<CardView>(R.id.cardView2)
        val cardViewXebuyt = rootView.findViewById<CardView>(R.id.cardView3)
        val cardViewXang = rootView.findViewById<CardView>(R.id.cardView4)
        val btnChuyen = rootView.findViewById<LinearLayout>(R.id.btnChuyen)

        buttonMenu.setOnClickListener {
            val popup =
                PopupMenu(this.requireContext(), buttonMenu)
            popup.menuInflater.inflate(R.menu.poupup_menu,popup.menu)
            popup.setOnMenuItemClickListener { item ->
                buttonMenu.setText(item.title)
                buttonMenu.setTextColor(Color.parseColor("#ffffff"))
                if(item.title.equals("VND"))
                {
                    textviewTien.setText("50,000")

                }
                if(item.title.equals("DOLLAR"))
                {
                    textviewTien.setText("2,13$")

                }
                true
            }

            popup.show();//showing popup menu
        }

        val viewpage = rootView.findViewById<ViewPager2>(R.id.viewPager2)
        viewpage.adapter = imgcoffe
        // viewpage.setPadding(0, 0, 130, 0);
        with(viewpage) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        btnChuyen.setOnClickListener {

        }

        cardViewDoAn.setOnClickListener {
            startActivity(Intent(this.requireContext(),BarnCodeActivity::class.java))
        }
        cardViewXebuyt.setOnClickListener {
            startActivity(Intent(this.requireContext(),BarnCodeActivity::class.java))
        }
        cardViewXang.setOnClickListener {
            startActivity(Intent(this.requireContext(),BarnCodeActivity::class.java))
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        viewpage.setPageTransformer { page, position ->
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }




        return rootView
    }


}