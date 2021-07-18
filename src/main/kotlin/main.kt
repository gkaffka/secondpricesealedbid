import domain.usecases.findWinningBuyerAndPrice
import domain.usecases.insertNewBuyer
import domain.usecases.insertNewProduct
import presentation.ui.AuctionCLI
import presentation.ui.consoleInterface

fun main(args: Array<String>) {
    /**
     * Starts the CLI injecting the necessary dependencies
     */
    AuctionCLI(
        insertNewProduct = insertNewProduct,
        insertNewBuyer = insertNewBuyer,
        findWinningBuyerAndPrice = findWinningBuyerAndPrice,
        consoleInterface = consoleInterface
    )
}