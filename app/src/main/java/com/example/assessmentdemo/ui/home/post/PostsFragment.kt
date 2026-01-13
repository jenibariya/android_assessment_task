package com.example.assessmentdemo.ui.home.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentdemo.R
import com.example.assessmentdemo.data.local.posts.PostEntity
import com.example.assessmentdemo.databinding.FragmentPostsBinding
import com.example.assessmentdemo.ui.home.comments.CommentsBottomSheetFragment

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding

    private val viewModel: PostsViewModel by activityViewModels()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

        binding.lifecycleOwner = this
        adapter = PostAdapter(object : PostItemListener {
            override fun onFavoriteClicked(post: PostEntity) {
                viewModel.toggleFavorite(post)
            }

            override fun onCommentClicked(post: PostEntity) {
                CommentsBottomSheetFragment(post.id)
                    .show(parentFragmentManager, "comments")
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null


        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadPosts()

        return binding.root
    }
}