import classes.Product
import classes.Shop

fun main() {
    println("Please provide a name for this shop: ")
    val shopName = readln()
    val myShop = Shop(shopName)
    println("Welcome to the Shop '${myShop.name}'")

    val options = listOf(
        "Create Product.",
        "List Products.",
        "Filter by Category.",
        "Show prices with discount.",
        "Calculate Total.",
        "Found the most expensive product.",
        "Group by category.",
        "Exit"
    )
    var selection = -1
    do {
        println("\nMenú:")
        options.forEachIndexed { index, option ->
            println("${index + 1} - $option")
        }


        print("Select an option: ")
        try {
            selection = readln().toInt()
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number.")
            continue
        }

        when (selection) {

            1 -> myShop.addProduct()

            2 -> myShop.listProducts()

            3 -> {
                println("Please provide a category to filter")
                val category = readlnOrNull() ?: ""
                myShop.filterProductsByCategory(category)
            }

            4 -> {
                println("Please provide the percentage of discount (1 to 99)")
                val discount = readln().toInt()
                myShop.showPricesWithDiscount(discount)
            }

            5 -> myShop.showTotalOfAllProducts()

            6 -> myShop.showMostExpensiveProduct()

            7 -> myShop.groupProductsByCategory()

            else -> break
        }

    } while (true)

}