package com.example.firebaselogin

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirebaseRepo {
    private val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()



    fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun createUser(): Task<AuthResult> {
        return firebaseAuth.signInAnonymously()
    }

    fun getallphones(): Task<QuerySnapshot> {
        return firebaseFirestore.collection("Phones").get()
    }
}