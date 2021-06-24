package com.yunjae.springhazelcastcache.entity

import org.springframework.data.annotation.Id

class Product(@Id var id:Int, var description: String, var price: Double) {
}