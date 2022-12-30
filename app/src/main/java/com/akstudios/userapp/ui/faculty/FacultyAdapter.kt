package com.akstudios.userapp.ui.faculty

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        holder.name.text = list[position].name
        holder.email.text = list[position].email
        holder.post.text = list[position].post
        holder.phNumber.text = list[position].phNumber
        Glide.with(context).load(list[position].url).into(holder.profilePic)
        holder.btnCallFaculty.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + list[position].phNumber)
            context.startActivity(callIntent)
        }
        holder.btnMailFaculty.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, list[position].email)
            //need this to prompts email client only
            email.type = "message/rfc822"
            context.startActivity(Intent.createChooser(email, "Choose an Email client :"))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}