package domain.usecases

import data.ProductRepository
import domain.entities.Product
import getPrice
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertNewProductTest {

    private lateinit var insertNewProduct: InsertNewProduct
    private val productRepository: ProductRepository = mock()

    @Before
    fun setup() {
        insertNewProduct = InsertNewProduct(productRepository)
    }

    @Test
    fun `when insertNewProduct is invoked the proper repository methods are called`() {
        val expectedProduct = Product(name = "A really cool product", reservePrice = getPrice(123.0))

        insertNewProduct.insertNewProduct(expectedProduct)

        verify(productRepository).initializeProduct(expectedProduct)
    }
}
