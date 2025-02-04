import classes.Product
import classes.Shop

fun main() {
    println("Please provide a name for this shop: ")
    val shopName = readln()
    val myShop = Shop(shopName)
    println("Welcome to the Shop '${myShop.name}'")

    val options = listOf("Create Product.", "List Products", "Exit")
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
                for (product in myShop.products){
                    println(product)
                }
            }
            3 -> break
        }

    } while (true)

}