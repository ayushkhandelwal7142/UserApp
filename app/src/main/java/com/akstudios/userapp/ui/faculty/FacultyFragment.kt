package com.akstudios.userapp.ui.faculty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.google.firebase.database.*


class FacultyFragment : Fragment() {
    private lateinit var mathsDeptRV: RecyclerView
    private lateinit var scienceDeptRV: RecyclerView
    private lateinit var englishDeptRV: RecyclerView
    private lateinit var mathsNoData: LinearLayout
    private lateinit var scienceNoData: LinearLayout
    private lateinit var englishNoData: LinearLayout
    private lateinit var list1: ArrayList<FacultyData>
    private lateinit var list2: ArrayList<FacultyData>
    private lateinit var list3: ArrayList<FacultyData>
    private lateinit var list4: ArrayList<FacultyData>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dbRef: DatabaseReference
    private lateinit var fAdapter: FacultyAdapter
    private lateinit var pbMAths: ProgressBar
    private lateinit var pbScience: ProgressBar
    private lateinit var pbEnglish: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faculty, container, false)

        mathsNoData = view.findViewById(R.id.mathsNoData)
        scienceNoData = view.findViewById(R.id.scienceNoData)
        englishNoData = view.findViewById(R.id.englishNoData)

        pbMAths = view.findViewById(R.id.pbMaths)
        pbScience = view.findViewById(R.id.pbScience)
        pbEnglish = view.findViewById(R.id.pbEnglish)

        mathsDeptRV = view.findViewById(R.id.mathsRV)
        scienceDeptRV = view.findViewById(R.id.scienceRV)
        englishDeptRV = view.findViewById(R.id.englishRV)

        list1 = arrayListOf()
        list2 = arrayListOf()
        list3 = arrayListOf()
        list4 = arrayListOf()

        databaseReference = FirebaseDatabase.getInstance().reference.child("Faculty")

        mathsDepartment()
        englishDepartment()
        scienceDepartment()

        return view
    }

    private fun mathsDepartment() {
        dbRef = databaseReference.child("Maths")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    pbMAths.visibility = View.GONE
                   mathsNoData.visibility = View.VISIBLE
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list1.add(data)
                        }
                        fAdapter = FacultyAdapter(list1, "Maths", this@FacultyFragment)
                        mathsDeptRV.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                            pbMAths.visibility = View.GONE
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun englishDepartment() {
        dbRef = databaseReference.child("English")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    pbEnglish.visibility = View.GONE
                    englishNoData.visibility = View.VISIBLE
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list2.add(data)
                        }
                        fAdapter = FacultyAdapter(list2, "English",  this@FacultyFragment)
                        englishDeptRV.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                            pbEnglish.visibility = View.GONE
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun scienceDepartment() {
        dbRef = databaseReference.child("Science")
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    pbScience.visibility = View.GONE
                    scienceNoData.visibility = View.VISIBLE
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(FacultyData::class.java)
                        if (data != null) {
                            list3.add(data)
                        }
                        fAdapter = FacultyAdapter(list3, "Science", this@FacultyFragment)
                        scienceDeptRV.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(context)
                            adapter = fAdapter
                            pbScience.visibility = View.GONE
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