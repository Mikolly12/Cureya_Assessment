package com.example.cureyaassessment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class Signin : Fragment() {

    lateinit var signup: TextView

    lateinit var email: TextView
    lateinit var password: TextView

    lateinit var BtnSignin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_signin, container, false)

        auth = FirebaseAuth.getInstance()

        signup = v.findViewById(R.id.tv_signup)

        signup.setOnClickListener {
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SignUp()).addToBackStack(null).commit()
        }

        email = v.findViewById(R.id.et_signin_email)
        password = v.findViewById(R.id.et_signin_password)
        BtnSignin = v.findViewById(R.id.btn_signin)

        BtnSignin.setOnClickListener {
            login()
        }

        return v
    }

    private fun login() {
        if (email.text.isEmpty()) {
            email.setError("Email is Required")
            email.requestFocus()
        }
        if (password.text.length < 6) {
            password.setError("Password should not be less than 6 characters")
            password.requestFocus()
        }

        if (!email.text.isEmpty() && password.text.length >= 6) {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful()) {
                        val intent = Intent(activity, MainActivity2::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            activity,
                            "Failed to login! Please check your credentials",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
