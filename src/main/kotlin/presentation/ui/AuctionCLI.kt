package presentation.ui


import domain.entities.Bid
import domain.entities.Buyer
import domain.entities.Price
import domain.entities.Product
import domain.usecases.FindWinningBuyerAndPrice
import domain.usecases.InsertNewBuyer
import domain.usecases.InsertNewProduct


private const val GET_PRODUCT_TO_AUCTION_NAME =
    "Enter a product to auction, enter a name for the product - ex: \"Cool product name\""
private const val GET_PRODUCT_TO_AUCTION_PRICE = "Enter the product's reserve price- ex: 42.42"
private const val GET_NUMBER_OF_BUYERS = "How many buyers are participating the auction? - ex: 5"
private const val GET_BUYER_NAME = "Enter the %dº buyer name - ex:\"Gabriel\""
private const val GET_NUMBER_OF_BIDS = "How many bids there are for this buyer? - ex: 3"
private const val GET_BUYER_BID = "Enter the %dº bid price- ex:\"130.0\""
private const val SHOW_AUCTION_RESULT =
    "*** Auction result for %s ***\nThe winning buyer is: %s\nThe winning price is: %s"
private const val NO_WINNING_BUYER = "No winning buyer"
private const val IS_RESERVE_PRICE = "(reserve price) %.2f"

class AuctionCLI(
    insertNewProduct: InsertNewProduct,
    insertNewBuyer: InsertNewBuyer,
    findWinningBuyerAndPrice: FindWinningBuyerAndPrice,
    private val consoleInterface: ConsoleInterface
) {

    /**
     *  Starts the CLI by:
     *  Adding a new product to be auctioned
     *  Adding the buyers and their bids
     *  Processing and displaying the auction result
     */
    init {
        val product = getProduct()
        insertNewProduct.insertNewProduct(product)
        getBuyers().forEach(insertNewBuyer::insertNewBuyer)
        showAuctionResult(findWinningBuyerAndPrice.getWinningBuyerAndPrice(), product)
    }

    /**
     *  Displays the auction result containing the winning buyer name and the winning price value
     */
    private fun showAuctionResult(result: Pair<Buyer?, Price>, product: Product) {
        val price = getAuctionResultPrice(result.second == product.reservePrice, result.second)
        consoleInterface.print(SHOW_AUCTION_RESULT.format(product.name, result.first?.name ?: NO_WINNING_BUYER, price))
    }

    /**
     *  Returns the formatted price auction result price
     */
    private fun getAuctionResultPrice(isReservePrice: Boolean, price: Price) =
            if (isReservePrice) IS_RESERVE_PRICE.format(price.amount) else "%.2f".format(price.amount)

    /**
     *  Get an instance of a Product
     */
    private fun getProduct() = Product(name = getProductName(), reservePrice = Price(getProductReservePrice()))

    /**
     *  Get a list of buyers (List<Buyer>) based on the number of buyers that are participating the auction
     */
    private fun getBuyers(): List<Buyer> = MutableList(getNumberOfBuyers())
    { Buyer(name = getBuyerName(it + 1), bids = getBuyerBids(getNumberOfBids())) }

    /**
     *  Get Product name
     */
    private fun getProductName() = consoleInterface.readString(GET_PRODUCT_TO_AUCTION_NAME)

    /**
     *  Get Product reserve price
     */
    private fun getProductReservePrice() = consoleInterface.readDouble(GET_PRODUCT_TO_AUCTION_PRICE)

    /**
     *  Get number of buyers
     */
    private fun getNumberOfBuyers() = consoleInterface.readInt(GET_NUMBER_OF_BUYERS)

    /**
     *  Get the buyer name
     */
    private fun getBuyerName(buyerNumber: Int) = consoleInterface.readString(GET_BUYER_NAME.format(buyerNumber))

    /**
     *  Get number of bids
     */
    private fun getNumberOfBids() = consoleInterface.readInt(GET_NUMBER_OF_BIDS)

    /**
     *  Get the bids entries for a given buyer according to the number of bids
     */
    private fun getBuyerBids(numberOfBids: Int): List<Bid> = MutableList(numberOfBids) {
        Bid(Price(consoleInterface.readDouble(GET_BUYER_BID.format(it + 1))))
    }
}
