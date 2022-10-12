package com.akstudios.userapp.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class GalleryFragment : Fragment() {
    private var list1: ArrayList<GalleryData> = arrayListOf()
    private var list3: ArrayList<String> = arrayListOf()
    private var list4: ArrayList<GalleryParentData> = arrayListOf()
    private var category: ArrayList<String> = arrayListOf()
    private lateinit var databaseReference: DatabaseReference
    private lateinit var galleryFragmentRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        galleryFragmentRV = view.findViewById(R.id.galleryFragmentRV)
        databaseReference = FirebaseDatabase.getInstance().reference.child("Gallery Images")

        getData()
        //getConvocationData()
        //getOthersData()

        return view
    }

    private fun getData() {
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(GalleryData::class.java)
                        if (data != null) {
                            list1.add(data)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        for (i in list1.indices) {
            list3.add(list1[i].url)
        }

    }

}