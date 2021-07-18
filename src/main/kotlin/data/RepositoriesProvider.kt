package data

/**
 *  Provides a usable instance of the repositories
 */

val buyersRepository by lazy { BuyersRepository() }
val productRepository by lazy { ProductRepository() }
