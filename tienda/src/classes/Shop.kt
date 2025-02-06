package classes

data class Shop(var name: String) {
    private val products = mutableListOf<Product>()

    fun addProduct() {
        println("Provide a product name:")
        val productName = readlnOrNull() ?: "Test Product"
        println("Provide a product price:")
        var productPrice = (readlnOrNull() ?: "0.0").toDouble()
        productPrice = if (productPrice >= 0) productPrice else 0.0
        println("Provide a product quantity:")
        var productQuantity = (readlnOrNull() ?: "0").toInt()
        productQuantity = if (productQuantity >= 0) productQuantity else 0
        println("Provide a product category:")
        val productCategory = readlnOrNull() ?: "Test"
        this.products.add(Product(productName, productPrice, productQuantity, productCategory))
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
}