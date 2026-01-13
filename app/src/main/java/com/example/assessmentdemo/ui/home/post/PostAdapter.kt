package com.example.assessmentdemo.ui.home.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentdemo.data.local.posts.PostEntity
import com.example.assessmentdemo.databinding.ItemPostBinding

class PostAdapter(
    private val listener: PostItemListener
) : ListAdapter<PostEntity, PostAdapter.PostViewHolder>(Diff()) {

    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostEntity) {
            binding.post = post

            binding.favoriteIcon.setOnClickListener {
                listener.onFavoriteClicked(post)
            }
            binding.commentIcon.setOnClickListener {
                listener.onCommentClicked(post)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class Diff : DiffUtil.ItemCallback<PostEntity>() {
        override fun areItemsTheSame(a: PostEntity, b: PostEntity) = a.id == b.id
        override fun areContentsTheSame(a: PostEntity, b: PostEntity) = a == b
    }
}
