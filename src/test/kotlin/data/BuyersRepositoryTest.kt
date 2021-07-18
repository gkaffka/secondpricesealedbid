package data

import domain.entities.Buyer
import getBids
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BuyersRepositoryTest {

    private lateinit var repository: BuyersRepository

    @Before
    fun setup() {
        repository = BuyersRepository()
    }

    @Test
    fun `when no buyers are added, getBuyers returns an empty list`() {
        val buyers = repository.getBuyers()

        assertEquals(emptyList<Buyer>(), buyers)
    }

    @Test
    fun `when one or more buyers are added, getBuyers returns a list with the added items`() {
        val buyerA = Buyer("A", listOf())
        val buyerB = Buyer("B", getBids(100.0, 123.0))
        val buyerC = Buyer("C", getBids(1000.0))
        val expectedBuyers = listOf(buyerA, buyerB, buyerC)

        repository.insertBuyer(buyerA, buyerB, buyerC)

        assertEquals(expectedBuyers, repository.getBuyers())
    }
}