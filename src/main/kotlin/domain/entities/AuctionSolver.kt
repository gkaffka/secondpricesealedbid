package domain.entities

class AuctionSolver {

    /**
     * Returns the winning Pair Buyer/Price given a product and a list of Buyers
     */
    fun getWinningPair(product: Product, buyers: List<Buyer>): Pair<Buyer?, Price> =
        getSortedBuyers(product, buyers).let {
            it.winningBuyer() to it.winningPrice(product)
        }

    /**
     * Returns a sorted descending list of Pair<Buyer, Bid> containing the buyer along with it's highest valid bid value.
     */
    private fun getSortedBuyers(product: Product, buyers: List<Buyer>): List<Pair<Buyer, Bid>> =
        buyers.mapNotNull { buyer -> buyer.bids.getHighestValidBid(product)?.let { bid -> buyer to bid } }
            .sortedByDescending { it.second.price.amount }

    /**
     * Returns a boolean indicating if the bid matches the product's reserve price
     */
    private fun Product.isBidValid(bid: Bid): Boolean = bid.price.amount >= reservePrice.amount

    /**
     * Returns the highest bid or null respecting the reserve price constraint from the product
     */
    private fun List<Bid>.getHighestValidBid(product: Product): Bid? = filter { product.isBidValid(it) }
        .maxByOrNull { it.price.amount }

    /**
     * Returns the buyer with the highest bid or null
     */
    private fun List<Pair<Buyer, Bid>>.winningBuyer(): Buyer? = firstOrNull()?.first

    /**
     * Returns the winning price or the reserve price if the conditions aren't met
     */
    private fun List<Pair<Buyer, Bid>>.winningPrice(product: Product): Price = when {
        size >= 2 -> this[1].second.price // get highest bid price from non-winning buyer if it exists
        else -> product.reservePrice
    }
}
