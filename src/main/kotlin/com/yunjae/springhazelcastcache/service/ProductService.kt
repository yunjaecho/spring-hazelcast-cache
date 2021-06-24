package com.yunjae.springhazelcastcache.service

import com.yunjae.springhazelcastcache.entity.Product
import com.yunjae.springhazelcastcache.repository.ProductRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class ProductService(private val repository: ProductRepository) {

    fun getAllProducts(): Flux<Product> {
        return repository.findAll();
    }

    fun getProductById(productId: Int): Mono<Product> {
        return repository.findById(productId)
    }

    fun createProduct(product: Product): Mono<Product> {
        return repository.save(product)
    }

    fun updateProduct(productId: Int, productMono: Mono<Product>): Mono<Product> {
        return repository.findById(productId)
            .flatMap { p: Product ->
                productMono.map { u: Product ->
                    p.description = u.description
                    p.price = u.price
                    p
                }
            }
            .flatMap { p: Product ->
                repository.save(
                    p
                )
            }
    }

    fun deleteProduct(id: Int): Mono<Void> {
        return repository.deleteById(id)
    }

}