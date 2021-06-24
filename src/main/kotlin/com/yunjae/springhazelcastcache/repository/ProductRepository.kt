package com.yunjae.springhazelcastcache.repository

import com.yunjae.springhazelcastcache.entity.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ProductRepository: ReactiveCrudRepository<Product, Int> {
}