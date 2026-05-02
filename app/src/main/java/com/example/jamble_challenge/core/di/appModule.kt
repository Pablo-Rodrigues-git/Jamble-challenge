package com.example.jamble_challenge.core.di

import com.example.jamble_challenge.core.dispatcher.AppDispatcher
import com.example.jamble_challenge.core.dispatcher.DefaultAppDispatcher
import com.example.jamble_challenge.data.repository.FakeProfileRepository
import com.example.jamble_challenge.domain.repository.ProfileRepository
import com.example.jamble_challenge.domain.usecase.GetBookmarksUseCase
import com.example.jamble_challenge.domain.usecase.GetLivesUseCase
import com.example.jamble_challenge.domain.usecase.GetReviewsUseCase
import com.example.jamble_challenge.domain.usecase.GetUserProfileUseCase
import com.example.jamble_challenge.domain.usecase.UpdateBioUseCase
import com.example.jamble_challenge.presentation.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppDispatcher> { DefaultAppDispatcher() }

    single<ProfileRepository> { FakeProfileRepository() }

    factory { GetUserProfileUseCase(repository = get()) }
    factory { GetLivesUseCase(repository = get()) }
    factory { GetReviewsUseCase(repository = get()) }
    factory { GetBookmarksUseCase(repository = get()) }
    factory { UpdateBioUseCase(repository = get()) }

    viewModel {
        ProfileViewModel(
            getUserProfile = get(),
            getLives = get(),
            getReviews = get(),
            getBookmarks = get(),
            updateBioUseCase = get(),
            dispatcher = get()
        )
    }
}
