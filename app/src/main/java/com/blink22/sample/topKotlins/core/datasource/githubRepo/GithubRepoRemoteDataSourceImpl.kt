package com.blink22.sample.topKotlins.core.datasource.githubRepo

import com.blink22.sample.topKotlins.core.api.GithubRepoApi
import com.blink22.sample.topKotlins.data.models.BaseResponse
import com.blink22.sample.topKotlins.data.models.GithubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepoRemoteDataSourceImpl @Inject constructor(
    private val api: GithubRepoApi
) : GithubRepoRemoteDataSource {

    override fun getTopRepositories(): Flow<BaseResponse<List<GithubRepo>>> {
       return flow { emit(api.getTopRepositories()) }
    }

}