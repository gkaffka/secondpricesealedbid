package domain.usecases

import data.BuyersRepository
import domain.entities.Buyer

class InsertNewBuyer(
    private val buyersRepository: BuyersRepository
) {
    /**
     * Insert a new buyer to participate the auction
     */
    fun insertNewBuyer(buyer: Buyer) {
        buyersRepository.insertBuyer(buyer)
    }
}
