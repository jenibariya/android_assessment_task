package com.example.assessmentdemo.ui.home.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentdemo.R
import com.example.assessmentdemo.data.local.posts.PostEntity
import com.example.assessmentdemo.databinding.FragmentFavoritesBinding
import com.example.assessmentdemo.ui.home.comments.CommentsBottomSheetFragment
import com.example.assessmentdemo.ui.home.post.PostAdapter
import com.example.assessmentdemo.ui.home.post.PostItemListener
import com.example.assessmentdemo.ui.home.post.PostsViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel: PostsViewModel by activityViewModels()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        observeFavorites()
        attachSwipeToDelete()

        return binding.root
    }

    private fun setupRecyclerView() {
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
    }

    private fun observeFavorites() {



        viewModel.favorites.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
            }
            adapter.submitList(it)
        }
    }

    private fun attachSwipeToDelete() {
        val swipeCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val post = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.toggleFavorite(post)
                }
            }

        ItemTouchHelper(swipeCallback)
            .attachToRecyclerView(binding.recyclerView)
    }
}