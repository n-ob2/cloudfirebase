package com.example.cloudfirebase

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.cloudfirebase.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private var dataText: TextView? = null
    private var nameText: EditText? = null
    private var db: FirebaseFirestore? = null
    private var people: CollectionReference? = null

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataText = findViewById(R.id.EditText)
        nameText = findViewById(R.id.nameText)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{

        }
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()
        people = db.collection("people")
        people!!.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var result = ""
                for (document in task.result!!) {
                    val data = document.data
                    result += (data["name"].toString() + " ["
                            + data["mail"].toString() + ":"
                            + data["age"].toString() + "]\n")
                }
                dataText!!.setText(result)
            } else {
                dataText!!.setText("can't load data...")
            }
        }
    }   //onCreate↑↑


    fun doAction(view: View?) {
        val fstr: String = nameText!!.toString() + ""
    }

    override fun onStart() {
        super.onStart()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    /*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
     */
}