import kotlin.random.Random

fun main(){
    val list = (1..100).toMutableList()
    list.remove(Random.nextInt(1, 100))

    println("Missing number: ${findMissingNumber(list)}")
}

fun findMissingNumber(listNumber: List<Int>): Int{
    val size = listNumber.size
    // (n+1)*(n+2)/2
    val expectationSum = ((size.plus(1)).times(size.plus(2))).div(2)
    val actualSum = listNumber.sum()

    return expectationSum.minus(actualSum)
}