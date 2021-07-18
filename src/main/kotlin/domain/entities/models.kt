package domain.entities

data class Bid(val price: Price)
data class Buyer(val name: String, val bids: List<Bid>)
data class Product(val name: String, val reservePrice: Price)
data class Price(val amount: Double)
