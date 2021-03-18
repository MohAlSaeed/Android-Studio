package com.example.recyclerviewfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private lateinit var db : FirebaseDatabase
//    lateinit var mRecyclerView: RecyclerView


    private val firebaseRepo : FirebaseRepo = FirebaseRepo()

    private var postlist: List<Phones> = ArrayList<Phones>()

    private val postListAdapter: PostListAdapter = PostListAdapter(postlist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        db = FirebaseDatabase.getInstance()
//        val ref = db.getReference("Profile")

        loadPostData()


        // init recycle view
        firebase_list.layoutManager = LinearLayoutManager(this)
        firebase_list.adapter = postListAdapter

    }

    private fun loadPostData() {
        firebaseRepo.getPostList().addOnCompleteListener {

            if (it.isSuccessful){
                postlist = it.result!!.toObjects(Phones::class.java)
                postListAdapter.postlistItems = postlist
                postListAdapter.notifyDataSetChanged()
            } else {
//                Log.d(TAG, "error: ${it.exception!!.message}")
                Toast.makeText(this, "done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}