package com.example.tugaspertemuan11retrofit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspertemuan11retrofit.model.Data
import com.squareup.picasso.Picasso

class UserAdapter(private val users: List<Data>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProfile: ImageView = view.findViewById(R.id.img_profile)
        val txtName: TextView = view.findViewById(R.id.txt_name)
        val txtEmail: TextView = view.findViewById(R.id.txt_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        val fullName = "${user.firstName} ${user.lastName}"

        holder.txtName.text = "Name: $fullName"
        holder.txtEmail.text = "Email: ${user.email}"

        // Load image using Picasso
        Picasso.get()
            .load(user.avatar)
            .centerCrop()
            .fit()
            .into(holder.imgProfile)

        // Set OnClickListener untuk membuka DetailActivity saat nama diklik
        holder.txtName.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("name", fullName)
            intent.putExtra("email", user.email)
            intent.putExtra("avatar", user.avatar)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}
