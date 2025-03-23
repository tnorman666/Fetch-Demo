package com.tyler_norman.fetch.di

import com.tyler_norman.fetch.data.FetchApiService
import com.tyler_norman.fetch.data.FetchRepositoryImpl
import com.tyler_norman.fetch.domain.repository.FetchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** Dependency injection module for various back-end infrastructure. Also defines and builds
 * [Retrofit] instance. Additional endpoints defined in [FetchApiService]. */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFetchApiService(retrofit: Retrofit): FetchApiService {
        return retrofit.create(FetchApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFetchRepository(apiService: FetchApiService): FetchRepository {
        return FetchRepositoryImpl(apiService)
    }
}
