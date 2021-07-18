package domain.usecases

import data.BuyersRepository
import data.ProductRepository
import domain.entities.AuctionSolver
import domain.entities.Buyer
import domain.entities.Price

class FindWinningBuyerAndPrice(
    private val buyersRepository: BuyersRepository,
    private val productRepository: ProductRepository,
    private val auctionSolver: AuctionSolver
) {

    /**
     * Returns the winning Pair Buyer/Price from the data provided by the BuyersRepository and the
     * ProductRepository repositories
     */
    fun getWinningBuyerAndPrice(): Pair<Buyer?, Price> =
        auctionSolver.getWinningPair(
            product = productRepository.getProduct(),
            buyers = buyersRepository.getBuyers()
        )
}
