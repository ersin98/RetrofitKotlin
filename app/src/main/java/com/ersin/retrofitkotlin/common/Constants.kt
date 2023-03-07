package com.ersin.retrofitkotlin.common


object Constants {
    const val BASE_URL="http://10.0.2.2:8080/"//http://localhost:8080/
    //10.0.2.2
    const val GET_PRODUCT = "api/products"
    const val GET_BY_CATEGORY="api/products/category/{categoryId}"
    const val GET_BY_QUERY_PRODUCT="api/products/{title}"
    const val ADD_PRODUCT="api/products"
    const val DELETE_PRODUCT="api/products/{id}"
    const val DELETE_ALL_PRODUCT="api/products"
    const val UPDATE_PRODUCTS="api/products"
    //categories
    const val GET_CATEGORY = "api/categories"
    const val ADD_CATEGORY="api/categories"
    const val DELETE_CATEGORY="api/categories/{id}"
    const val DELETE_ALL_CATEGORY="api/categories"
    const val UPDATE_CATEGORY="api/categories"
}