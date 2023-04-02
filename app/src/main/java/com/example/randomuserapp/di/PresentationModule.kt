package com.example.randomuserapp.di

import com.example.randomuserapp.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module  {
    viewModel { HomeViewModel(get()) }
}
