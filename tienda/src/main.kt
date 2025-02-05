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

            1 -> {
                println("Provide a product name:")
                val productName = readlnOrNull() ?: "Test Product"
                println("Provide a product price:")
                val productPrice = (readlnOrNull() ?: "0.0").toDouble()
                println("Provide a product quantity:")
                val productQty = (readlnOrNull() ?: "0").toInt()
                println("Provide a product category:")
                val productCategory = readlnOrNull() ?: "Test"
                myShop.products.add(Product(productName, productPrice, productQty, productCategory))
            }

            2 -> {
                for (product in myShop.products) {
                    println(product)
                }
            }

            3 -> {
                println("Please provide a category to filter")
                val category = readln()
                val filteredList = myShop.products.filter { it.category.equals(category, ignoreCase = true) }.toList()
                println(filteredList)
            }

            4 -> {
                println("Please provide the percentage of discount (1 to 99)")
                val discount = readln().toInt()
                val discountedList = myShop.products.map { it.price * ((100 - discount) / 100.0) }.toList()
                println(discountedList)
            }

            5 -> {
                val result = myShop.products.fold(0.0) { acc, product -> acc + (product.price * product.quantity) }
                println("The total of all products is $result")
            }

            6 -> {
                val mostExpensive = myShop.products.maxByOrNull { it.price * it.quantity }
                println("The most expensive item is: $mostExpensive")
            }

            7 -> {
                myShop.products.groupBy { it.category }.forEach { (category, productsInCategory) ->
                    println("Category $category")
                    productsInCategory.forEach{ println(it) }
                }
            }

            else -> break
        }

    } while (true)

}