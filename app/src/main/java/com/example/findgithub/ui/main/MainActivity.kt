package com.example.findgithub.ui.main

import MainAdapter
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findgithub.data.model.User
import com.example.findgithub.databinding.ActivityMainBinding
import com.example.findgithub.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)

        binding.apply {
            rcvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvUser.setHasFixedSize(true)
            rcvUser.adapter = adapter

            btnSearch.setOnClickListener {

            }

            binding.etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    findUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }
        viewModel.getFindUsers().observe(this, {
            if (it != null)
                adapter.setListUser(it)
            showLoading(false)
        })

    }

    private fun findUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setFindUser(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.prgsBar.visibility = View.VISIBLE
        } else {
            binding.prgsBar.visibility = View.GONE
        }
    }

}

