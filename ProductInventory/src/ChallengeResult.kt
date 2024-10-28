import java.math.BigDecimal
import java.math.RoundingMode

object ChallengeResult {
    fun List<Product>.totalInventoryValue() = BigDecimal(this.sumOf { it.price.times(it.quantity) })
        .setScale(2, RoundingMode.HALF_UP)
        .toDouble()

    fun List<Product>.productWithHeightPrice() = this.maxBy { it.price }.name
    fun List<Product>.findProduct(keyword: String) = this.any { it.name.contains(keyword) }
    fun List<Product>.sortOrder(
        byPrice: Boolean = false,
        descendingOrder: Boolean = false,
    ): List<Product> {
        return if (descendingOrder) {
            if (byPrice) {
                this.sortedByDescending { it.price }
            } else {
                this.sortedByDescending { it.quantity }
            }
        } else {
            if (byPrice) {
                this.sortedBy { it.price }
            } else {
                this.sortedBy { it.quantity }
            }
        }
    }
}