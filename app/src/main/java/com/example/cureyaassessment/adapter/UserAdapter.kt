package com.example.cureyaassessment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cureyaassessment.Profile
import com.example.cureyaassessment.R
import com.example.cureyaassessment.User
import com.squareup.picasso.Picasso

class UserAdapter(private val mList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.userName.text = itemsViewModel.name
        holder.userEmail.text = itemsViewModel.email
        holder.userCard.setOnClickListener {
            val fragment: Fragment = Profile()
            val bundle = Bundle()

            bundle.putString("name", itemsViewModel.name)
            bundle.putString("phone", itemsViewModel.phone)
            bundle.putString("email", itemsViewModel.email)
            fragment.setArguments(bundle)
            (holder.itemView.context as FragmentActivity).getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container2, fragment).addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var userCard: CardView = ItemView.findViewById(R.id.card_user)
        var userName: TextView = ItemView.findViewById(R.id.card_user_name)
        val userEmail: TextView = ItemView.findViewById(R.id.card_user_email)
    }

}