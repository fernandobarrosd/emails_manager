package com.fernando.emailsmanager_gerenciadorde_emails.ui.screens.emails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.ItemEmailBinding
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email

class EmailAdapter(
    private val emails: List<Email>,
    private val onClickListener: (Email) -> Unit
) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = emails.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val email = emails[position]
        viewHolder.bind(email)
    }

    inner class ViewHolder(private val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: Email) {
            with(email) {
                binding.emailNameText.text = name
                binding.emailValueText.text = value
            }
        }
    }
}