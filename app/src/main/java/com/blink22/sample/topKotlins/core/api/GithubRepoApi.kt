package com.blink22.sample.topKotlins.core.api

import com.blink22.sample.topKotlins.data.models.BaseResponse
import com.blink22.sample.topKotlins.data.models.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepoApi {

    /**
     * Get Github Repositories base on the following:
     * - "language:kotlin" => get by kotlin language only
     * - "stars" => sort by stars
     * - "desc" => order by the highest to the lowest (stars)
     * - "per_page" => get only 10 repositories by single request
     * */
    @GET("repositories")
    suspend fun getTopRepositories(
        @Query("q") searchText: String = "language:kotlin",
        @Query("sort") sortBy: String = "stars",
        @Query("order") order: String = "desc",
        @Query("per_page") itemPerPage: Int = 10
    ): BaseResponse<List<GithubRepo>>

}
