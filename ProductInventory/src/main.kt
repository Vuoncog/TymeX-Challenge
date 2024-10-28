import ChallengeResult.findProduct
import ChallengeResult.productWithHeightPrice
import ChallengeResult.sortOrder
import ChallengeResult.totalInventoryValue

fun main() {
    val listProduct = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3),
    )

    println("2a ${listProduct.totalInventoryValue()}")
    println("2b ${listProduct.productWithHeightPrice()}")
    println("2c ${listProduct.findProduct("Headphones")}")
    println("2d1 ${listProduct.sortOrder(byPrice = true)}")
    println("2d2 ${listProduct.sortOrder(byPrice = true, descendingOrder = true)}")
    println("2d3 ${listProduct.sortOrder(byPrice = false)}")
    println("2d4 ${listProduct.sortOrder(byPrice = false, descendingOrder = true)}")
}