package com.blink22.sample.topKotlins.core.datasource.githubRepo

import com.blink22.sample.topKotlins.data.models.BaseResponse
import com.blink22.sample.topKotlins.data.models.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepoRemoteDataSource {

    fun getTopRepositories(): Flow<BaseResponse<List<GithubRepo>>>

}