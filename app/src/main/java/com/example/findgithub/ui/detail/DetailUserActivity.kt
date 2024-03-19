package com.example.findgithub.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.findgithub.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        // Panggil showLoading(true) sebelum memperoleh detail pengguna
        showLoading(true)

        username?.let { viewModel.setUserDetail(it) }

        viewModel.getUserDetail().observe(this, { user ->
            user?.let {
                with(binding) {
                    tvName.text = it.name
                    txtUname.text = it.login
                    txtCountFollowers.text = "${it.followers} Followers"
                    txtCountFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivUser)
                }
                // Panggil showLoading(false) setelah mendapatkan detail pengguna
                showLoading(false)
            }
        })

        val sectionPagerAdapter = PagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        // Menambahkan fungsionalitas tombol kembali
        binding.btnBack.setOnClickListener {
            onBackPressed()
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
