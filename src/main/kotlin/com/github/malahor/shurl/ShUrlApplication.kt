package com.github.malahor.shurl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShUrlApplication

fun main(args: Array<String>) {
	runApplication<ShUrlApplication>(*args)
}
