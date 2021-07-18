import domain.entities.Bid
import domain.entities.Price

fun getPrice(amount: Double) = Price(amount = amount)
fun getBids(vararg amount: Double) = amount.map { Bid(getPrice(it)) }
