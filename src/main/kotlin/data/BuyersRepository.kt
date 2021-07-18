package data

import domain.entities.Buyer

class BuyersRepository {

    private var buyers = mutableListOf<Buyer>()

    /**
     * Add a buyer to the list of bidding buyers
     */
    fun insertBuyer(vararg buyer: Buyer) = buyer.forEach(buyers::add)

    /**
     * Returns the list of bidding buyers
     */
    fun getBuyers(): List<Buyer> = buyers

}
