package com.blink22.sample.topKotlins.domain

import com.blink22.sample.topKotlins.data.repositories.githubRepo.GithubRepoRepository
import javax.inject.Inject


class GetTopRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepoRepository
) {
    operator fun invoke() = repository.getTopRepositories()
}