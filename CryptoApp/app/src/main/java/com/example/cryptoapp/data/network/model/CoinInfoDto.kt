package com.example.cryptoapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "full_price_list")
data class CoinInfoDto(
    @SerializedName("TYPE")
    @Expose
    var type: String,

    @SerializedName("MARKET")
    @Expose
    var market: String,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    var fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    var toSymbol: String,

    @SerializedName("FLAGS")
    @Expose
    var flags: String,

    @SerializedName("LASTMARKET")
    @Expose
    var lastMarket: String,

    @SerializedName("MEDIAN")
    @Expose
    var median: Double,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    var topTierVolume24hour: Double,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    var topTierVolume24hourTo: Double,

    @SerializedName("LASTTRADEID")
    @Expose
    var lastTradeId: String,

    @SerializedName("PRICE")
    @Expose
    var price: Double,

    @SerializedName("LASTUPDATE")
    @Expose
    var lastUpdate: Long,

    @SerializedName("LASTVOLUME")
    @Expose
    var lastVolume: Double,

    @SerializedName("LASTVOLUMETO")
    @Expose
    var lastVolumeTo: Double,

    @SerializedName("VOLUMEHOUR")
    @Expose
    var volumeHour: Double,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    var volumeHourTo: Double,

    @SerializedName("OPENHOUR")
    @Expose
    var openHour: Double,

    @SerializedName("HIGHHOUR")
    @Expose
    var highHour: Double,

    @SerializedName("LOWHOUR")
    @Expose
    var lowHour: Double,

    @SerializedName("VOLUMEDAY")
    @Expose
    var volumeDay: Double,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    var volumeDayTo: Double,

    @SerializedName("OPENDAY")
    @Expose
    var openDay: Double,

    @SerializedName("HIGHDAY")
    @Expose
    var highDay: Double,

    @SerializedName("LOWDAY")
    @Expose
    var lowDay: Double,

    @SerializedName("VOLUME24HOUR")
    @Expose
    var volume24Hour: Double,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    var volume24HourTo: Double,

    @SerializedName("OPEN24HOUR")
    @Expose
    var open24Hour: Double,

    @SerializedName("HIGH24HOUR")
    @Expose
    var high24Hour: Double,

    @SerializedName("LOW24HOUR")
    @Expose
    var low24Hour: Double,

    @SerializedName("CHANGE24HOUR")
    @Expose
    var change24Hour: Double,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    var changePct24Hour: Double,

    @SerializedName("CHANGEDAY")
    @Expose
    var changeDay: Double,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    var changePctDay: Double,

    @SerializedName("CHANGEHOUR")
    @Expose
    var changeHour: Double,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    var changePctHour: Double,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    var conversionType: String,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    var conversionSymbol: String,

    @SerializedName("CONVERSIONLASTUPDATE")
    @Expose
    var conversionLastUpdate: Int,

    @SerializedName("SUPPLY")
    @Expose
    var supply: Int,

    @SerializedName("MKTCAP")
    @Expose
    var mktCap: Double,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    var mktCapPenalty: Int,

    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    var circulatingSupply: Int,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    var circulatingSupplyMktCap: Double,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    var totalVolume24h: Double,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    var totalVolume24Hto: Double,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    var totalTopTierVolume24h: Double,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    var totalTopTierVolume24Hto: Double,

    @SerializedName("IMAGEURL")
    @Expose
    var imageUrl: String
) {
}