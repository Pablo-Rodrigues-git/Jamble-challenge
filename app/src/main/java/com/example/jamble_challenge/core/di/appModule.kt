package com.example.jamble_challenge.core.di

import com.example.jamble_challenge.data.repository.FakeProfileRepository
import com.example.jamble_challenge.domain.repository.ProfileRepository
import com.example.jamble_challenge.presentation.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProfileRepository> {
        FakeProfileRepository()
    }

    viewModel {
        ProfileViewModel(
            repository = get()
        )
    }
}