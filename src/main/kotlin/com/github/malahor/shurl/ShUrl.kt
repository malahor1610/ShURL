package com.github.malahor.shurl

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("shurl")
class ShUrl(@Id val short: String, @Indexed val long: String)