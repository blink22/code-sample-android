package com.blink22.sample.topKotlins.data.models

import com.google.gson.annotations.SerializedName

data class GithubRepo(

    @SerializedName("full_name")
    val name: String,

    val description: String,

    @SerializedName("stargazers_count")
    val starsCount: Int,

    val language: String,

    @SerializedName("forks_count")
    val forksCount: Int,

    val owner: RepoOwner

) {
    val ownerName: String get() = owner.name
}