package presentation.ui

import domain.entities.Buyer
import domain.entities.Product
import domain.usecases.FindWinningBuyerAndPrice
import domain.usecases.InsertNewBuyer
import domain.usecases.InsertNewProduct
import getBids
import getPrice
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class AuctionCLITest {

    private val insertNewProduct: InsertNewProduct = mock()
    private val insertNewBuyer: InsertNewBuyer = mock()
    private val findWinningBuyerAndPrice: FindWinningBuyerAndPrice = mock {
        on { getWinningBuyerAndPrice() } doReturn Pair(null, getPrice(100.0))
    }
    private val consoleInterface: ConsoleInterface = mock {
        on { readDouble(any()) } doReturn 100.0
        on { readString(any()) } doReturn "name"
        on { readInt(any()) } doReturn 5
    }
    private val expectedProduct = Product("name", getPrice(100.0))
    private val expectedBuyer = Buyer("name", getBids(100.0, 100.0, 100.0, 100.0, 100.0))
    private val expectedAuctionResult =
        "*** Auction result for name ***\nThe winning buyer is: No winning buyer\nThe winning price is: (reserve price) %.2f".format(
            100.0
        )

    private lateinit var auctionCLI: AuctionCLI

    @Before
    fun setup() {
        auctionCLI = AuctionCLI(
            insertNewProduct = insertNewProduct,
            insertNewBuyer = insertNewBuyer,
            findWinningBuyerAndPrice = findWinningBuyerAndPrice,
            consoleInterface = consoleInterface
        )
    }

    @Test
    fun `when the AuctionCLI is initialized, the proper use cases are invoked`() {
        verify(insertNewProduct).insertNewProduct(expectedProduct)
        verify(insertNewBuyer, times(5)).insertNewBuyer(expectedBuyer)
        verify(findWinningBuyerAndPrice).getWinningBuyerAndPrice()
        verify(consoleInterface).print(expectedAuctionResult)
    }
}