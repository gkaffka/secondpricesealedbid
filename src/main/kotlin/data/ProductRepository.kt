package data

import domain.entities.Product


open class ProductRepository {

    private lateinit var product: Product

    /**
     * Initialize a product
     */
    fun initializeProduct(product: Product) {
        this.product = product
    }

    /**
     * Returns the available biddable product
     */
    fun getProduct(): Product = product

}
