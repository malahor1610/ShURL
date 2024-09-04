package com.github.malahor.shurl

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShUrlRepository : CrudRepository<ShUrl, String> {
    fun findByLong(long: String): ShUrl?
}