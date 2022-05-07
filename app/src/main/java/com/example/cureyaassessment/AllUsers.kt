package com.example.cureyaassessment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cureyaassessment.adapter.UserAdapter
import com.google.firebase.firestore.FirebaseFirestore

class AllUsers : Fragment() {

    lateinit var rvUser: RecyclerView

    private lateinit var db: FirebaseFirestore

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_all_users, container, false)

        rvUser = v.findViewById(R.id.rv_users)

        db = FirebaseFirestore.getInstance()

        rvUser.layoutManager = LinearLayoutManager(activity)
        rvUser.setHasFixedSize(true)

        fetchUsers()

        return v;
    }

    private fun fetchUsers() {

        val userArraylist = ArrayList<User>()

        db.collection("Users").get().addOnSuccessListener {
            for (i in it.documents.indices) {
                val u: User? = it.documents.get(i).toObject(User::class.java)
                userArraylist.add(u!!)
//                Log.d("working", it.documents.get(i).data.toString())
            }
            userAdapter = UserAdapter(userArraylist)
            rvUser.adapter = userAdapter
        }
    }



}