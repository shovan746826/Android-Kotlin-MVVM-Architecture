package com.ex.loginregistration.roomDatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ex.loginregistration.R
import com.ex.loginregistration.databinding.RecyclerViewLayoutBinding
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable
import com.ex.loginregistration.roomDatabase.viewInterface.RecyclerViewInterface

class RecyclerViewNoteAdapter(private val noteList: List<NoteTable>, private var recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<RecyclerViewNoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_layout,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.recyclerViewLayoutBinding.data = noteList[position]
        holder.recyclerViewLayoutBinding.imageViewDelete.setOnClickListener {
            recyclerViewInterface.onDeleteItemClick(holder.recyclerViewLayoutBinding.imageViewDelete,noteList[position])
        }
    }

    override fun getItemCount() = noteList.size


    inner class NoteViewHolder(var recyclerViewLayoutBinding: RecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(recyclerViewLayoutBinding.root)
}
