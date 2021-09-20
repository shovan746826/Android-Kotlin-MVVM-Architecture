package com.ex.loginregistration.roomDatabase.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.loginregistration.R
import com.ex.loginregistration.databinding.ActivityRoomDatabaseBinding
import com.ex.loginregistration.globals.toast
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable
import com.ex.loginregistration.roomDatabase.adapters.RecyclerViewNoteAdapter
import com.ex.loginregistration.roomDatabase.viewInterface.RecyclerViewInterface
import com.ex.loginregistration.roomDatabase.viewInterface.RoomDatabaseInterface
import com.ex.loginregistration.roomDatabase.viewModel.NoteViewModel
import kotlinx.android.synthetic.main.activity_room_database.*

class RoomDatabaseActivity : AppCompatActivity(), RoomDatabaseInterface, RecyclerViewInterface {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRoomDatabaseBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_room_database)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.roomDatabaseInterface = this

        setObserver()
    }

    private fun setObserver(){
        viewModel.liveDataGetAllNote.observe(this, {listNote ->
            recyclerView.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = RecyclerViewNoteAdapter(listNote,this)
            }
        })
    }

    override fun onStarted() {

    }

    override fun onSuccess(message: String) {
        Handler(Looper.getMainLooper()).post {
            toast(message)
        }
    }

    override fun onFailure(message: String) {
        Handler(Looper.getMainLooper()).post {
            toast(message)
        }
    }

    override fun onDeleteItemClick(view: View, noteTable: NoteTable) {
        when(view.id){
            R.id.imageViewDelete -> viewModel.deleteNote(noteTable)
        }

    }
}