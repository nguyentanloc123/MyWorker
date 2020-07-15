package com.example.myworker.FragmentContanier

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myworker.R
import com.example.myworker.UserData.HistoryAdapter
import com.example.myworker.UserData.UserWorkAdd
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FragmentUser : Fragment()
{
 //   private lateinit
    private lateinit var loclist : List<UserWorkAdd>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.userfragment, container, false)
        linearLayoutManager = LinearLayoutManager(this.requireContext())

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyceviewWorker)
        recyclerView.layoutManager = linearLayoutManager
        // var
        GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        database = Firebase.database.reference
        val itemList:MutableList<UserWorkAdd> = ArrayList()


        val usersdRef: DatabaseReference = database.child("users")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (ds in dataSnapshot.children) {
                        val post = ds.child("thongtindodung")
                        for (loc in post.children)
                        {
                            var postTemp = loc.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
                            itemList.add(postTemp)
                            recyclerView.adapter = HistoryAdapter(itemList)
                            Log.d("item",postTemp.toString())
                        }
                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        usersdRef.addValueEventListener(eventListener)

        return  rootView
    }
    fun historyList(listData: UserWorkAdd){



    }




     class GetDataFromFirebase : AsyncTask<Void?, Void?, Boolean>() {
         override fun doInBackground(vararg params: Void?): Boolean {
             return false
         }
//        override fun onPreExecute() {
//            super.onPreExecute()
//        }
//
//        protected override fun doInBackground(vararg voids: Void): Boolean {
//            return false
//        }
//
//        override fun onPostExecute(aBoolean: Boolean) {
//            super.onPostExecute(aBoolean)
//        }
    }
}