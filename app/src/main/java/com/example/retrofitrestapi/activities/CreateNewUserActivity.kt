package com.example.retrofitrestapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitrestapi.R
import com.example.retrofitrestapi.User
import com.example.retrofitrestapi.viewModels.CreateNewUserViewModel
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {
    private lateinit var viewModel: CreateNewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)

        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        createUserObservable()

        if (user_id != null) {
            loadUserData(user_id)
        }

        create_button.setOnClickListener {
            createUser(user_id)
        }
        delete_button.setOnClickListener {
            deleteUser(user_id)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)
    }

    private fun loadUserData(user_id: String?) {
        viewModel.getLoadUserDataObservable().observe(this, {
            if (it != null) {
                edittext_name.setText(it.data?.name)
                edittext_email.setText(it.data?.email)
                create_button.text = "Update"
                delete_button.visibility = View.VISIBLE
            }
        })
        viewModel.getUserData(user_id)
    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObservable().observe(this, {
            if (it == null) {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Failed to create/update any user...",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Successfully created/updated new user",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        })
    }

    private fun deleteUser(user_id: String?) {
        viewModel.getDeleteUserObservable().observe(this, {
            if (it == null) {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Failed to delete user...",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Successfully deleted user",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        })
        viewModel.deleteUser(user_id)
    }

    private fun createUser(user_id: String?) {
        val user = User(
            "",
            edittext_name.text.toString(),
            edittext_email.text.toString(),
            "Active",
            "Male"
        )

        if (user_id.isNullOrEmpty()) {
            viewModel.createUser(user)
        }
        else {
            viewModel.updateUser(user_id, user)
        }
    }
}