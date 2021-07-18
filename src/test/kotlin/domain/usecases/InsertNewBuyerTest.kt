package domain.usecases

import data.BuyersRepository
import domain.entities.Buyer
import getBids
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertNewBuyerTest {

    private lateinit var insertNewBuyer: InsertNewBuyer
    private val buyersRepository: BuyersRepository = mock()

    @Before
    fun setup() {
        insertNewBuyer = InsertNewBuyer(buyersRepository)
    }

    @Test
    fun `when insertNewBuyer is invoked the proper repository methods are called`() {
        val buyerA = Buyer("A", getBids(100.0))

        insertNewBuyer.insertNewBuyer(buyerA)

        verify(buyersRepository).insertBuyer(buyerA)
    }
}
