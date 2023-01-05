package com.akstudios.userapp.ui.faculty

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.bumptech.glide.Glide


class FacultyAdapter(
    private val list: ArrayList<FacultyData>,
    val category: String,
    val context: FacultyFragment
) : RecyclerView.Adapter<FacultyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.facultyNameRV)
        val email: TextView = itemView.findViewById(R.id.facultyEmailRV)
        val post: TextView = itemView.findViewById(R.id.facultyPostRV)
        val profilePic: ImageView = itemView.findViewById(R.id.facultyImage)
        val phNumber: TextView = itemView.findViewById(R.id.facultyContactNoRV)
        val btnCallFaculty: ImageView = itemView.findViewById(R.id.btnCallFaculty)
        val btnMailFaculty: ImageView = itemView.findViewById(R.id.btnMailFaculty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.update_faculty_cv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list[position].url.isNotEmpty()) {
            Glide.with(context).load(list[position].url).into(holder.profilePic)
        }
        holder.name.text = list[position].name
        holder.email.text = list[position].email
        holder.post.text = list[position].post
        holder.phNumber.text = list[position].phNumber
        holder.btnCallFaculty.setOnClickListener {
            if (ContextCompat.checkSelfPermission(context.requireContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context.requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE), 100)
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + list[position].phNumber)
                context.startActivity(callIntent)
            }
        }
        holder.btnMailFaculty.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.data = Uri.parse("mailto:")
            email.type = "text/plain"
            email.putExtra(Intent.EXTRA_EMAIL, list[position].email)
            try {
                context.startActivity(Intent.createChooser(email, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                ex.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}