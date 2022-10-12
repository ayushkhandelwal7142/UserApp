package com.akstudios.userapp.ui.ebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.database.*

class EbookActivity : AppCompatActivity() {
    private lateinit var ebookRV: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var fAdapter:EbookAdapter
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var shimmerLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebook)

        ebookRV = findViewById(R.id.ebookRV)
        databaseReference = FirebaseDatabase.getInstance().reference
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container)
        shimmerLayout = findViewById(R.id.shimmer_layout)
        getEbookData()
    }

    private fun getEbookData() {
        val reference = databaseReference.child("pdf")
        reference.addValueEventListener(object: ValueEventListener {
            var list: ArrayList<EbookData> = arrayListOf()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    //to be written
                } else {
                    for (i in snapshot.children) {
                        val data = i.getValue(EbookData::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                        fAdapter = EbookAdapter(this@EbookActivity, list)
                        ebookRV.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@EbookActivity)
                            adapter = fAdapter
                            shimmerFrameLayout.stopShimmer()
                            shimmerLayout.visibility = View.GONE
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }
}