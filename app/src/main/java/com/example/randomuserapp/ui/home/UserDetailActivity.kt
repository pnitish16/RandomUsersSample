package com.example.randomuserapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuserapp.R
import com.example.randomuserapp.databinding.ActivityMainBinding
import com.example.randomuserapp.databinding.ActivityUserDetailBinding
import com.example.randomuserapp.domain.model.UserItem
import com.example.randomuserapp.util.Constants
import com.example.randomuserapp.util.Extensions.parcelable

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userItem: UserItem
    private lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.hasExtra(Constants.USER_BUNDLE)) {
            val bundle = intent.getBundleExtra(Constants.USER_BUNDLE)
            bundle?.parcelable<UserItem>(Constants.USER_OBJECT)?.let {
                userItem = it
            }
        }

        if (::userItem.isInitialized) {
            setData(userItem)
        }

    }

    private fun setData(userItem: UserItem) {
        binding.userItem = userItem
    }
}