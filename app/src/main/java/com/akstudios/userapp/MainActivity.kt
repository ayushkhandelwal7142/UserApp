package com.akstudios.userapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.akstudios.userapp.loginScreen.LoginActivity
import com.akstudios.userapp.notiication.Constants
import com.akstudios.userapp.ui.ebook.EbookActivity
import com.akstudios.userapp.ui.videoLectures.VideoLecturesActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navigationDrawer: NavigationView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.frame_layout)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationDrawer = findViewById(R.id.navigation_drawer)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Start, R.string.Close)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        userName = sharedPreferences.getString("userName", "User").toString()
        supportActionBar?.title = "Welcome $userName"
        // setting user name in navigation view header layout
        val header = navigationDrawer.getHeaderView(0)
        header.findViewById<TextView>(R.id.txtUserName).text = "Hello $userName"

        navigationDrawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_video -> {
                    val intent = Intent(this, VideoLecturesActivity::class.java)
                    startActivity(intent)
                }
                R.id.navigation_attendance -> {
                    val url = ""  // logged in users' class
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                }
                R.id.navigation_website -> Toast.makeText(
                    applicationContext,
                    "Website",
                    Toast.LENGTH_LONG
                ).show()
                R.id.navigation_review -> Toast.makeText(
                    applicationContext,
                    "Review",
                    Toast.LENGTH_LONG
                ).show()
                R.id.navigation_eBook -> {
                    val intent = Intent(this, EbookActivity::class.java)
                    startActivity(intent)
                }
                R.id.navigation_log_out -> {
                    logOut()
                }
            }
            true
        }
    }

    private fun logOut() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Sign Out?")
            setMessage("Do you really want to sign out?")
            setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if (firebaseAuth.currentUser != null) {
                        firebaseAuth.signOut()
                        if (firebaseAuth.currentUser == null) {
                            Toast.makeText(
                                this@MainActivity,
                                "User signed out successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Something went wrong. Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "User not signed in", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            })
            setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
        }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}