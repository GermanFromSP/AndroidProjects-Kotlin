package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
   suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "YOR_API_KEY",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ):CoinNamesListDto

    @GET("pricemultifull")
   suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "YOR_API_KEY",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): CoinInfoJsonContainerDto

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"

        private const val CURRENCY = "USD"
    }
}