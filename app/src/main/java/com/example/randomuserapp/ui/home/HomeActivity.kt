package com.example.randomuserapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import com.example.randomuserapp.R
import com.example.randomuserapp.data.network.model.ApiUserItem
import com.example.randomuserapp.databinding.ActivityMainBinding
import com.example.randomuserapp.domain.model.UserItem
import com.example.randomuserapp.ui.home.adapter.UserListPagingAdapter
import com.example.randomuserapp.ui.home.viewmodel.HomeViewModel
import com.example.randomuserapp.util.Constants.USER_BUNDLE
import com.example.randomuserapp.util.Constants.USER_OBJECT
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(R.layout.activity_main) {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userListPagingAdapter: UserListPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViews()

        setupObservers()
    }

    private fun setupObservers() {
        homeViewModel.isLoading.observe(this) {
            binding.loadingProgress.isVisible = it
        }

        lifecycleScope.launch {
           homeViewModel.getRandomUsers().collect {
               userListPagingAdapter.submitData(it.map { it.toUserItem() })
           }
        }

        userListPagingAdapter.addLoadStateListener { loadState ->
            binding.errorMsg.isVisible = loadState.refresh is LoadState.Error
        }
    }

    private fun setupViews() {
        binding.apply {
            lifecycleOwner = this@HomeActivity

            userListPagingAdapter =
                UserListPagingAdapter(
                    object : UserListPagingAdapter.OnItemClickListener {
                        override fun onItemClick(userItem: UserItem) {
                            //open detail page
                            val detailIntent = Intent(this@HomeActivity, UserDetailActivity::class.java)
                            val bundle = Bundle()
                            bundle.putParcelable(USER_OBJECT, userItem)
                            detailIntent.putExtra(USER_BUNDLE, bundle)
                            startActivity(detailIntent)
                        }
                    })
            userListRv.apply {
                adapter = userListPagingAdapter
            }
        }
    }
}