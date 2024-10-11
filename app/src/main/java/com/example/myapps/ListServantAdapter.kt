package com.example.myapps

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListServantAdapter(private val listServant: ArrayList<Servant>) :
    RecyclerView.Adapter<ListServantAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_servant)
        val tvName: TextView = itemView.findViewById(R.id.tv_servant_name)
        val tvKelas: TextView = itemView.findViewById(R.id.tv_class_name)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_item_description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.fate_servant_layout, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listServant.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(nama, kelas, photo, desc) = listServant[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = nama
        holder.tvKelas.text = kelas
        holder.tvDeskripsi.text = desc

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_hero", listServant[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

}