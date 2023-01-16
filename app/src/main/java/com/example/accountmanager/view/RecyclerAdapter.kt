package com.example.accountmanager.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accountmanager.R
import com.example.accountmanager.model.AccountObject
import kotlinx.android.synthetic.main.account_object_card.view.*

class RecyclerAdapter(private val recyclerList:List<AccountObject>, private val listener: onAccountClickerListener): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val logo: ImageView = view.logo
        val accountPlatform: TextView = view.platform
        val accountUsername: TextView = view.username

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onAccountClickerListener(position)
            }
        }
    }

    interface onAccountClickerListener {
        fun onAccountClickerListener(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_object_card, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentAccount = recyclerList[position]
        holder.logo.setImageResource(R.drawable.ic_launcher_foreground)
        holder.accountPlatform.text = currentAccount.platform
        holder.accountUsername.text = currentAccount.username
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }
}