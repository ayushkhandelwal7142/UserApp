package com.akstudios.userapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.akstudios.userapp.ui.ebook.EbookActivity
import com.akstudios.userapp.ui.ebook.EbookAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navigationDrawer: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.frame_layout)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationDrawer = findViewById(R.id.navigation_drawer)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Start, R.string.Close)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationDrawer.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_developer -> Toast.makeText(applicationContext, "Developer", Toast.LENGTH_LONG).show()
                R.id.navigation_video -> Toast.makeText(applicationContext, "Video Lectures", Toast.LENGTH_LONG).show()
                R.id.navigation_website -> Toast.makeText(applicationContext, "Website", Toast.LENGTH_LONG).show()
                R.id.navigation_theme -> Toast.makeText(applicationContext, "Themes", Toast.LENGTH_LONG).show()
                R.id.navigation_review -> {
                    val intent = Intent(this, EbookActivity::class.java)
                    startActivity(intent)
                }
                R.id.navigation_eBook-> Toast.makeText(applicationContext, "E-Books", Toast.LENGTH_LONG).show()
                else -> {
                    Toast.makeText(this, "None of the above", Toast.LENGTH_LONG).show()
                }
            }
            return@setNavigationItemSelectedListener true
        }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
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