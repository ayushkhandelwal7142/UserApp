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


class GalleryFragment : Fragment() {
    private var list1: ArrayList<String> = arrayListOf()
    private var list2: ArrayList<String> = arrayListOf()
    private var list3: ArrayList<String> = arrayListOf()
    private var category: ArrayList<String> = arrayListOf()
    private lateinit var annualFunctionRV: RecyclerView
    private lateinit var independenceDayRV: RecyclerView
    private lateinit var republicDayRV: RecyclerView
    private lateinit var othersRV: RecyclerView
    private lateinit var fAdapter: GalleryAdapter
    private lateinit var databaseReference: DatabaseReference
    private lateinit var galleryFragmentRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        annualFunctionRV = view.findViewById(R.id.annualFunctionRV)
        independenceDayRV = view.findViewById(R.id.independenceDayRV)
        republicDayRV = view.findViewById(R.id.republicDayRV)
        othersRV = view.findViewById(R.id.otherFunctionsRV)
        databaseReference = FirebaseDatabase.getInstance().reference.child("Gallery Images")

        getAnnualFunctionData()
        getIndependenceDayData()
        getRepublicDayData()
        getOthersData()

        return view
    }

    private fun getOthersData() {
        val reference = databaseReference.child("Other Events")
        reference.addValueEventListener(object : ValueEventListener {
            val list: ArrayList<GalleryData> = arrayListOf()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "other events no data found", Toast.LENGTH_LONG).show()
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(GalleryData::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                    }
                    fAdapter = GalleryAdapter(this@GalleryFragment, list)
                    othersRV.layoutManager = GridLayoutManager(context, 3)
                    othersRV.adapter = fAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // show toast
            }

        })
    }

    private fun getRepublicDayData() {
        val reference = databaseReference.child("Republic Day")
        reference.addValueEventListener(object : ValueEventListener {
            val list: ArrayList<GalleryData> = arrayListOf()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(GalleryData::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                    }
                    fAdapter = GalleryAdapter(this@GalleryFragment, list)
                    republicDayRV.layoutManager = GridLayoutManager(context, 3)
                    republicDayRV.adapter = fAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // show toast
            }

        })
    }

    private fun getIndependenceDayData() {
        val reference = databaseReference.child("Independence Day")
        reference.addValueEventListener(object : ValueEventListener {
            val list: ArrayList<GalleryData> = arrayListOf()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(GalleryData::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                    }
                    fAdapter = GalleryAdapter(this@GalleryFragment, list)
                    independenceDayRV.layoutManager = GridLayoutManager(context, 3)
                    independenceDayRV.adapter = fAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // show toast
            }

        })
    }

    private fun getAnnualFunctionData() {
        val reference = databaseReference.child("Annual Function")
        reference.addValueEventListener(object : ValueEventListener {
            val list: ArrayList<GalleryData> = arrayListOf()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(GalleryData::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                    }
                    fAdapter = GalleryAdapter(this@GalleryFragment, list)
                    annualFunctionRV.layoutManager = GridLayoutManager(context, 3)
                    annualFunctionRV.adapter = fAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // show toast
            }

        })
    }
}