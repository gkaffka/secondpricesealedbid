package domain.usecases

import data.BuyersRepository
import data.ProductRepository
import domain.entities.AuctionSolver
import domain.entities.Buyer
import domain.entities.Product
import getBids
import getPrice
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FindWinningBuyerAndPriceTest {

    private lateinit var findWinningBuyerAndPrice: FindWinningBuyerAndPrice
    private val expectedBuyers = listOf(Buyer("A", getBids(10.0)))
    private val expectedProduct = Product("product", getPrice(100.0))
    private val buyersRepository: BuyersRepository = mock { on { getBuyers() } doReturn expectedBuyers }
    private val productRepository: ProductRepository = mock { on { getProduct() } doReturn expectedProduct }
    private val auctionSolver: AuctionSolver = mock()

    @Before
    fun setup() {
        findWinningBuyerAndPrice = FindWinningBuyerAndPrice(buyersRepository, productRepository, auctionSolver)
    }

    @Test
    fun `when getWinningBuyerAndPrice is invoked the proper repositories and auctionSolver methods is called`() {
        findWinningBuyerAndPrice.getWinningBuyerAndPrice()

        verify(buyersRepository).getBuyers()
        verify(productRepository).getProduct()
        verify(auctionSolver).getWinningPair(expectedProduct, expectedBuyers)
    }
}
