package com.blink22.sample.topKotlins.data.models


/**
 * [BaseResponse] is the base response that is wrapping Github API data.
 * */
data class BaseResponse<T>(val items: T)