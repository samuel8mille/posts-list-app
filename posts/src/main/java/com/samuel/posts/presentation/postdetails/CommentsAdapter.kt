package com.samuel.posts.presentation.postdetails

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.presentation.inflate
import com.samuel.posts.R
import com.samuel.posts.presentation.model.CommentItem

class CommentsAdapter :
    ListAdapter<CommentItem, CommentsAdapter.ViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_comment)) {

        fun bind(item: CommentItem) {
//            itemView.setAva
        }
    }

}

private class CommentDiffCallback : DiffUtil.ItemCallback<CommentItem>() {
    override fun areItemsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean =
        oldItem == newItem

}