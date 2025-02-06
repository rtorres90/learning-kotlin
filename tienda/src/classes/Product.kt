package classes

data class Product(var name:String, var price: Double=0.0, var quantity:Int=0, var category: String?=null){
    fun printDetails() {
        println("Product Details:")
        println("Name: $name")
        println("Price: $price")
        println("Quantity: $quantity")
        println("Category: ${category ?: "Not specified"}")
        println("--------------------")
    }
}
