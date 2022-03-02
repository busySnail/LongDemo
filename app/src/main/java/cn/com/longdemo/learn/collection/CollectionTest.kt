package cn.com.longdemo.learn.collection


fun main() {


    /**
     * reduce & fold
     */
    val numbers = listOf(5, 2, 10, 4)

    val sum = numbers.reduce { sum, element -> sum + element }
    println(sum)

    val sumDoubled = numbers.fold(2) { sum, element -> sum + element * 2 }
    println(sumDoubled)


    /**
     * min & max
     */
    val min3Remainder = numbers.minByOrNull { it % 3 }
    println(min3Remainder)

    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWithOrNull(compareBy { it.length })
    println(longestString)

    /**
     * getOrElse & getOrNull
     */

    numbers.getOrElse(4) { 3 }
    numbers.getOrNull(3)

    /**
     * sort
     */
    val strs = mutableListOf("one", "two", "three", "four")
    strs.shuffle()
    strs.sortBy { it.length }
    strs.sortDescending()


}

class CollectionTest {}