package com.blink22.sample.topKotlins.data.repositories.githubRepo

import com.blink22.sample.topKotlins.data.models.BaseResponse
import com.blink22.sample.topKotlins.data.models.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepoRepository {

    fun getTopRepositories(): Flow<BaseResponse<List<GithubRepo>>>

}