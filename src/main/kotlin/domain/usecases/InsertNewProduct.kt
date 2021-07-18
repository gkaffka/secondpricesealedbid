package domain.usecases

import data.ProductRepository
import domain.entities.Product

class InsertNewProduct(
    private val productRepository: ProductRepository
) {
    /**
     * Insert a new product to auction
     */
    fun insertNewProduct(product: Product) {
        productRepository.initializeProduct(product)
    }
}
