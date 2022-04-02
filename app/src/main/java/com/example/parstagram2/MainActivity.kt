package com.example.parstagram2

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagram2.fragments.ComposeFragment
import com.example.parstagram2.fragments.FeedFragment
import com.example.parstagram2.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

/*
were going let the user create a post by taking a photo with their camera
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fragmentManager: FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item->

            var fragmentToShow: Fragment? = null
           when(item.itemId) {
               //three different of ids we need to hangle
               R.id.action_home -> {

                   fragmentToShow = FeedFragment()

               }
               R.id.action_compose -> {
                   fragmentToShow = ComposeFragment()
                   //Toast.makeText(this, "compose", Toast.LENGTH_SHORT).show()
               }
               R.id.action_profile -> {
                   fragmentToShow = ProfileFragment()
                  // Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
               }

           }
            if(fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //this returning true signifies we've handled this user interaction
            true
        }
        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
        }

    // queryPosts()
    //send a Post object to our Parse server





    companion object {
        const val TAG = "MainActivity"
    }
}
//there might be problems with brackets placement