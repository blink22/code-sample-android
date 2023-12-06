package com.blink22.sample.topKotlins.data.models

import com.google.gson.annotations.SerializedName


data class RepoOwner(
    @SerializedName("login")
    val name: String
)