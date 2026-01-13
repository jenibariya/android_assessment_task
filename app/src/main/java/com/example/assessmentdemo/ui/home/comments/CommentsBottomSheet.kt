package com.example.assessmentdemo.ui.home.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentdemo.R
import com.example.assessmentdemo.databinding.BottomSheetCommentsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsBottomSheetFragment(
    private val postId: Int
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetCommentsBinding

    private val viewModel: CommentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_comments, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CommentsAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getCommentsByPost(postId).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}

