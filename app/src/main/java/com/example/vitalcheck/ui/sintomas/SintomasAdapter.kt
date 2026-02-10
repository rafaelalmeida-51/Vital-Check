package com.example.vitalcheck.ui.sintomas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vitalcheck.R
import com.example.vitalcheck.data.local.entity.SintomasEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SintomasAdapter : RecyclerView.Adapter<SintomasAdapter.VH>() {

    private val itens = mutableListOf<SintomasEntity>()
    private val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))

    fun submitList(newItems: List<SintomasEntity>) {
        itens.clear()
        itens.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sintoma, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(itens[position], sdf)
    }

    override fun getItemCount(): Int = itens.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvData: TextView = itemView.findViewById(R.id.tvData)
        private val tvTexto: TextView = itemView.findViewById(R.id.tvTexto)

        fun bind(item: SintomasEntity, sdf: SimpleDateFormat) {
            tvData.text = sdf.format(Date(item.timestampMillis))
            tvTexto.text = item.text
        }
    }
}