package com.blink22.sample.topKotlins.core.di

import com.blink22.sample.topKotlins.core.datasource.githubRepo.GithubRepoRemoteDataSource
import com.blink22.sample.topKotlins.core.datasource.githubRepo.GithubRepoRemoteDataSourceImpl
import com.blink22.sample.topKotlins.core.api.GithubRepoApi
import com.blink22.sample.topKotlins.data.repositories.githubRepo.GithubRepoRepository
import com.blink22.sample.topKotlins.core.repositories.githubRepo.GithubRepoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubRepoModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): GithubRepoApi {
        return retrofit.create(GithubRepoApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        api: GithubRepoApi
    ): GithubRepoRemoteDataSource {
        return GithubRepoRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun providesRepository(
        remoteDataSource: GithubRepoRemoteDataSource
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(
            remoteDataSource
        )
    }

}