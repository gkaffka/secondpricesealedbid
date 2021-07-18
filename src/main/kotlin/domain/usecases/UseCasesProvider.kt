package domain.usecases

import data.buyersRepository
import data.productRepository
import domain.entities.AuctionSolver
import java.util.*

/**
 *  Provides a usable instance of the use cases
 */

val findWinningBuyerAndPrice by lazy {
    FindWinningBuyerAndPrice(
        buyersRepository = buyersRepository,
        productRepository = productRepository,
        auctionSolver = AuctionSolver()
    )
}
val insertNewProduct by lazy {
    InsertNewProduct(
        productRepository = productRepository
    )
}

val insertNewBuyer by lazy {
    InsertNewBuyer(
        buyersRepository = buyersRepository
    )
}
