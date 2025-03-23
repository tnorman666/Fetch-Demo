package com.tyler_norman.fetch.di

import com.tyler_norman.fetch.domain.repository.FetchRepository
import com.tyler_norman.fetch.domain.usecases.FetchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Dependency injection module for the Fetch App's use-cases. */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchUseCase(
        fetchRepository: FetchRepository,
    ): FetchUseCase = FetchUseCase(
        fetchRepository = fetchRepository
    )
}
