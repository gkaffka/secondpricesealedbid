package domain.entities


import getBids
import getPrice
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

private const val PRODUCT_RESERVE_PRICE = 100.0

class AuctionSolverTest {

    private val auctionSolver = AuctionSolver()
    private val product = Product(name = "product", reservePrice = getPrice(PRODUCT_RESERVE_PRICE))

    @Test
    fun `when buyers list is empty, returns no winning Buyer and the reserve price`() {
        val buyers = emptyList<Buyer>()

        val pair = auctionSolver.getWinningPair(buyers = buyers, product = product)

        assertNull(pair.first) // No winning buyers
        assertEquals(product.reservePrice, pair.second) // Winning price is the reserve price
    }

    @Test
    fun `when there is only a single buyer with a valid bid, returns it and the reserve price`() {
        val buyers = listOf(Buyer("A", getBids(150.0)))

        val pair = auctionSolver.getWinningPair(buyers = buyers, product = product)

        assertEquals(buyers.first(), pair.first) // Winning buyer is the only buyer wth valid bids
        assertEquals(product.reservePrice, pair.second) // Winning price is the reserve price
    }

    @Test
    fun `when there are multiple buyers with no valid bids, returns no winning Buyer and the reserve price`() {
        // reserve price is 100.0
        val buyerA = Buyer("A", getBids(90.0, 13.0, 0.0)) // non-winning highest bidder (130)
        val buyerB = Buyer("B", getBids(1.0, 2.0, 3.0))
        val buyerC = Buyer("C", getBids(99.99))
        val buyerD = Buyer("D", emptyList())
        val buyerE = Buyer("E", getBids(12.0, 15.0, 67.0)) // highest bidder (140)
        val buyers = listOf(buyerA, buyerB, buyerC, buyerD, buyerE)

        val pair = auctionSolver.getWinningPair(buyers = buyers, product = product)

        assertNull(pair.first) // No winning buyers
        assertEquals(product.reservePrice, pair.second) // Winning price is the reserve price
    }

    @Test
    fun `when there are multiple buyers with a valid bid, returns the highest bidder and the highest bid price from a non-winning buyer`() {
        // reserve price is 100.0
        val buyerA = Buyer("A", getBids(110.0, 130.0)) // non-winning highest bidder (130)
        val buyerB = Buyer("B", listOf())
        val buyerC = Buyer("C", getBids(125.0))
        val buyerD = Buyer("D", getBids(105.0, 115.0, 90.0))
        val buyerE = Buyer("E", getBids(132.0, 135.0, 140.0)) // highest bidder (140)
        val buyers = listOf(buyerA, buyerB, buyerC, buyerD, buyerE)

        val pair = auctionSolver.getWinningPair(buyers = buyers, product = product)

        assertEquals(buyerE, pair.first) // Buyer E is the winning buyer
        assertEquals(130.0, pair.second.amount) // Winning price is the highest bid from non-winning buyer A
    }

    @Test
    fun `when there are multiple buyers with the same valid bid, returns the first buyer from the list`() {
        // reserve price is 100.0
        val buyerA = Buyer("A", getBids(140.0))
        val buyerB = Buyer("B", getBids(140.0))
        val buyerC = Buyer("C", getBids(140.0))
        val buyerD = Buyer("D", getBids(140.0))

        val buyers = listOf(buyerA, buyerB, buyerC, buyerD)

        val pair = auctionSolver.getWinningPair(buyers = buyers, product = product)

        assertEquals(buyerA, pair.first) // Buyer E is the winning buyer
        assertEquals(140.0, pair.second.amount) // Winning price is the highest bid from non-winning buyer
    }
}
