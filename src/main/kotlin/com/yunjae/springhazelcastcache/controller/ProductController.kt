package com.yunjae.springhazelcastcache.controller

import com.yunjae.springhazelcastcache.entity.Product
import com.yunjae.springhazelcastcache.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("product")
class ProductController(private val productService: ProductService) {

    @GetMapping("all")
    fun getAll(): Flux<Product> {
        return productService.getAllProducts()
    }

//    @GetMapping("{productId}")
//    fun getProductById(@PathVariable productId: Int): Mono<ResponseEntity<Product>> {
//        return productService.getProductById(productId)
//            .map(ResponseEntity::ok)
//            .defaultIfEmpty(ResponseEntity.notFound().build())
//    }

    @PostMapping
    fun createProduct(@RequestBody productMono: Mono<Product>): Mono<Product> {
        return productMono.flatMap(productService::createProduct)
    }

    @PutMapping("{productId}")
    fun updateProduct(
        @PathVariable productId: Int,
        @RequestBody productMono: Mono<Product>
    ): Mono<Product> {
        return productService.updateProduct(productId, productMono)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): Mono<Void> {
        return productService.deleteProduct(id)
    }
}