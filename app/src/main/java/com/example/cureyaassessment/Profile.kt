package com.example.cureyaassessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class Profile : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var phone: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()


        db = FirebaseFirestore.getInstance()

        name = v.findViewById(R.id.profile_user_name)
        email = v.findViewById(R.id.profile_user_email)
        phone = v.findViewById(R.id.profile_user_phone)

        val currentUser = FirebaseAuth.getInstance().currentUser?.uid

        var logoutB = v.findViewById<Button>(R.id.btn_logout)
    logoutB.setOnClickListener{
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }




        var bundle = getArguments()
        if (bundle?.getString("name") == null) {
            db.collection("Users").document(currentUser!!).get().addOnSuccessListener {
                Log.d("working", it.toString())
                setUserDetails(
                    it.get("name").toString(),
                    it.get("email").toString(),
                    it.get("phone").toString()
                )
            }
        } else {
            setUserDetails(
                bundle?.getString("name")!!,
                bundle?.getString("email")!!,
                bundle?.getString("phone")!!,

            )
        }


        return v
    }

    private fun setUserDetails(n: String, e: String, p: String) {
        name.text = n
        email.text = e
        phone.text = p

    }

}