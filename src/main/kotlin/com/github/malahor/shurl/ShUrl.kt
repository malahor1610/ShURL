package com.github.malahor.shurl

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("shurl")
class ShUrl(@Id val short: String, val long: String)