package com.blink22.sample.topKotlins.core.repositories.githubRepo

import com.blink22.sample.topKotlins.core.datasource.githubRepo.GithubRepoRemoteDataSource
import com.blink22.sample.topKotlins.data.models.BaseResponse
import com.blink22.sample.topKotlins.data.models.GithubRepo
import com.blink22.sample.topKotlins.data.repositories.githubRepo.GithubRepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubRepoRemoteDataSource
) : GithubRepoRepository {

    override fun getTopRepositories(): Flow<BaseResponse<List<GithubRepo>>> {
        return remoteDataSource.getTopRepositories()
    }

}