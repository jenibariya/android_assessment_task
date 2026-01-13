package com.example.assessmentdemo.ui.home.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentdemo.data.local.comments.CommentEntity
import com.example.assessmentdemo.data.model.CommentDto
import com.example.assessmentdemo.databinding.ItemCommentBinding

class CommentsAdapter :
    ListAdapter<CommentEntity, CommentsAdapter.CommentViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommentViewHolder(
        private val binding: ItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentEntity) {
            binding.comment = comment
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CommentEntity>() {
        override fun areItemsTheSame(
            oldItem: CommentEntity,
            newItem: CommentEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CommentEntity,
            newItem: CommentEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}

