package classes

import java.io.File
import java.io.FileReader
import java.io.FileWriter

data class Shop(var name: String) {
    private val products = mutableListOf<Product>()
    private val file = File("products.txt")

    init {
        loadProductsFromText()
    }

    fun addProduct() {
        println("Provide a product name:")
        val productName = readlnOrNull()?: "Test Product"
        println("Provide a product price:")
        var productPrice = (readlnOrNull()?: "0.0").toDouble()
        productPrice = if (productPrice >= 0) productPrice else 0.0
        println("Provide a product quantity:")
        var productQuantity = (readlnOrNull()?: "0").toInt()
        productQuantity = if (productQuantity >= 0) productQuantity else 0
        println("Provide a product category:")
        val productCategory = readlnOrNull()?: "Test"
        val newProduct = Product(productName, productPrice, productQuantity, productCategory)
        this.products.add(newProduct)
        saveProductsToText()
    }

    fun listProducts(products: List<Product>? = null) {
        (products ?: this.products).forEach { println(it) }
    }

    fun filterProductsByCategory(category: String) {
        val filteredList = this.products.filter { it.category.equals(category, ignoreCase = true) }.toList()
        this.listProducts(filteredList)
    }

    fun showPricesWithDiscount(discount: Int) {
        val discountedList =
            this.products.map { "${it.name} from ${it.price} to ${it.price * ((100 - discount) / 100.0)}" }.toList()
        println(discountedList)
    }

    fun showTotalOfAllProducts() {
        val result = this.products.fold(0.0) { acc, product -> acc + (product.price * product.quantity) }
        println("The total of all products is $result")
    }

    fun showMostExpensiveProduct() {
        val mostExpensive = this.products.maxByOrNull { it.price * it.quantity }
        if (mostExpensive != null) {
            println(
                "The most expensive item is ${mostExpensive.name}, " +
                        "it would cost ${(mostExpensive.price * mostExpensive.quantity)}"
            )
        }
    }

    fun groupProductsByCategory() {
        this.products.groupBy { it.category }.forEach { (category, productsInCategory) ->
            println("Category $category")
            this.listProducts(productsInCategory)
        }
    }

    private fun loadProductsFromText() {
        if (file.exists()) {
            try {
                val reader = FileReader(file)
                val lines = reader.readLines()
                reader.close()

                products.clear() // Clear existing products to avoid duplicates
                lines.forEach { line ->
                    val values = line.split("|") // Using "|" as the delimiter. Change as needed.
                    if (values.size == 4) { // Check for correct number of values
                        try {
                            val name = values[0]
                            val price = values[1].toDoubleOrNull() ?: 0.0
                            val quantity = values[2].toIntOrNull() ?: 0
                            val category = values[3]
                            products.add(Product(name, price, quantity, category))
                        } catch (e: NumberFormatException) {
                            println("Error parsing a product line: $line.  Skipping.")
                        }
                    } else {
                        println("Invalid product line format: $line. Skipping.")
                    }
                }
            } catch (e: Exception) {
                println("Error loading products from text file: ${e.message}")
            }
        }
    }

    private fun saveProductsToText() {
        try {
            val writer = FileWriter(file)
            products.forEach { product ->
                writer.write("${product.name}|${product.price}|${product.quantity}|${product.category}\n")
            }
            writer.close()
        } catch (e: Exception) {
            println("Error saving products to text file: ${e.message}")
        }
    }
}