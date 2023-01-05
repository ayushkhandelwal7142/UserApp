package com.akstudios.userapp.ui.notice

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.MainActivity
import com.akstudios.userapp.R
import com.google.firebase.database.*

class NoticeFragment : Fragment() {

    private lateinit var noticeRV: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var noticeData: ArrayList<NoticeData> = arrayListOf()
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_notice, container, false)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Notice")
        noticeRV = view.findViewById(R.id.noticeRV)
        progressBar = view.findViewById(R.id.progressBar)
        getData()
        return view
    }

    private fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    return
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(NoticeData::class.java)
                        if (data != null) {
                            noticeData.add(0, data)
                        }
                        noticeRV.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = NoticeAdapter(this@NoticeFragment, noticeData)
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error" + error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}