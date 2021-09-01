package com.example.retrofitrestapi.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrestapi.R
import com.example.retrofitrestapi.User
import com.example.retrofitrestapi.adapters.RecyclerViewAdapter
import com.example.retrofitrestapi.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
        searchUser()

        create_user_button.setOnClickListener {
            startActivity(Intent(this, CreateNewUserActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration = DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)

            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, {
            if (it == null) {
                Toast.makeText(
                    this@MainActivity,
                    "No result found...",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUsersList()
    }

    private fun searchUser() {
        search_button.setOnClickListener {
            if (search_edittext.text.toString().isNotEmpty()) {
                viewModel.searchUser(search_edittext.text.toString())
            }
            else {
                viewModel.getUsersList()
            }
        }
    }

    override fun onItemEditClick(user: User) {
        val intent = Intent(this@MainActivity, CreateNewUserActivity::class.java)
        intent.putExtra("user_id", user.id)
        startActivityForResult(intent,  100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            viewModel.getUsersList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}