package com.example.cryptoapp.domain

data class CoinInfo(
    var fromSymbol: String,
    var toSymbol: String,
    var lastMarket: String,
    var price: Double,
    var lastUpdate: String,
    var highDay: Double,
    var lowDay: Double,
    var imageUrl: String
)