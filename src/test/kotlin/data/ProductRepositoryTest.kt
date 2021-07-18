package data

import domain.entities.Product
import getPrice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        repository = ProductRepository()
    }

    @Test
    fun `when no product is added, getProduct returns an UninitializedPropertyAccessException`() {
        try {
            repository.getProduct()
        } catch (e: Exception) {
            assertTrue(e is UninitializedPropertyAccessException)
        }
    }

    @Test
    fun `when a product is added, getProduct returns the added product`() {
        val expectedProduct = Product("name", getPrice(122.0))

        repository.initializeProduct(expectedProduct)

        assertEquals(expectedProduct, repository.getProduct())
    }
}
