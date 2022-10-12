package com.akstudios.userapp.ui.faculty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.google.firebase.database.*


class FacultyFragment : Fragment() {
    private lateinit var cseDept: RecyclerView
    private lateinit var eceDept: RecyclerView
    private lateinit var mechDept: RecyclerView
    private lateinit var cseNoData: LinearLayout
    private lateinit var eceNoData: LinearLayout
    private lateinit var mechNoData: LinearLayout
    private lateinit var list1: ArrayList<FacultyData>
    private lateinit var list2: ArrayList<FacultyData>
    private lateinit var list3: ArrayList<FacultyData>
    private lateinit var list4: ArrayList<FacultyData>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dbRef: DatabaseReference
    private lateinit var fAdapter: FacultyAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faculty, container, false)

        cseNoData = view.findViewById(R.id.cseNoData)
        mechNoData = view.findViewById(R.id.meNoData)
        eceNoData = view.findViewById(R.id.eceNoData)
        cseDept = view.findViewById(R.id.cseRV)
        mechDept = view.findViewById(R.id.meRV)
        eceDept = view.findViewById(R.id.eceRV)


        list1 = arrayListOf()
        list2 = arrayListOf()
        list3 = arrayListOf()
        list4 = arrayListOf()

        databaseReference = FirebaseDatabase.getInstance().reference.child("Faculty")

        cseDepartment()
        eceDepartment()
        meDepartment()

        return view
    }

    private fun cseDepartment() {
        dbRef = databaseReference.child("CSE")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                   cseNoData.visibility = View.VISIBLE
                    cseDept.visibility = View.GONE
                } else {
                    cseNoData.visibility = View.GONE
                    cseDept.visibility = View.VISIBLE
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list1.add(data)
                        }
                        fAdapter = FacultyAdapter(list1, "CSE", this@FacultyFragment)
                        cseDept.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun eceDepartment() {
        dbRef = databaseReference.child("ECE")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    eceNoData.visibility = View.VISIBLE
                    eceDept.visibility = View.GONE
                } else {
                    eceNoData.visibility = View.GONE
                    eceDept.visibility = View.VISIBLE
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list2.add(data)
                        }
                        fAdapter = FacultyAdapter(list2, "ECE",  this@FacultyFragment)
                        mechDept.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun meDepartment() {
        dbRef = databaseReference.child("ME")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    mechNoData.visibility = View.VISIBLE
                    mechDept.visibility = View.GONE
                } else {
                    mechNoData.visibility = View.GONE
                    mechDept.visibility = View.VISIBLE
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list3.add(data)
                        }
                        fAdapter = FacultyAdapter(list3, "ME", this@FacultyFragment)
                        mechDept.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}