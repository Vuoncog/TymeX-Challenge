package com.vuoncog.data.response

import com.google.gson.annotations.SerializedName

data class SymbolsResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("symbols") val symbols: Symbols? = Symbols(),
    @SerializedName("error" ) val error : Error? = null
) {
    data class Symbols(
        @SerializedName("AED") val AED: String? = null,
        @SerializedName("AFN") val AFN: String? = null,
        @SerializedName("ALL") val ALL: String? = null,
        @SerializedName("AMD") val AMD: String? = null,
        @SerializedName("ANG") val ANG: String? = null,
        @SerializedName("AOA") val AOA: String? = null,
        @SerializedName("ARS") val ARS: String? = null,
        @SerializedName("AUD") val AUD: String? = null,
        @SerializedName("AWG") val AWG: String? = null,
        @SerializedName("AZN") val AZN: String? = null,
        @SerializedName("BAM") val BAM: String? = null,
        @SerializedName("BBD") val BBD: String? = null,
        @SerializedName("BDT") val BDT: String? = null,
        @SerializedName("BGN") val BGN: String? = null,
        @SerializedName("BHD") val BHD: String? = null,
        @SerializedName("BIF") val BIF: String? = null,
        @SerializedName("BMD") val BMD: String? = null,
        @SerializedName("BND") val BND: String? = null,
        @SerializedName("BOB") val BOB: String? = null,
        @SerializedName("BRL") val BRL: String? = null,
        @SerializedName("BSD") val BSD: String? = null,
        @SerializedName("BTC") val BTC: String? = null,
        @SerializedName("BTN") val BTN: String? = null,
        @SerializedName("BWP") val BWP: String? = null,
        @SerializedName("BYN") val BYN: String? = null,
        @SerializedName("BYR") val BYR: String? = null,
        @SerializedName("BZD") val BZD: String? = null,
        @SerializedName("CAD") val CAD: String? = null,
        @SerializedName("CDF") val CDF: String? = null,
        @SerializedName("CHF") val CHF: String? = null,
        @SerializedName("CLF") val CLF: String? = null,
        @SerializedName("CLP") val CLP: String? = null,
        @SerializedName("CNY") val CNY: String? = null,
        @SerializedName("CNH") val CNH: String? = null,
        @SerializedName("COP") val COP: String? = null,
        @SerializedName("CRC") val CRC: String? = null,
        @SerializedName("CUC") val CUC: String? = null,
        @SerializedName("CUP") val CUP: String? = null,
        @SerializedName("CVE") val CVE: String? = null,
        @SerializedName("CZK") val CZK: String? = null,
        @SerializedName("DJF") val DJF: String? = null,
        @SerializedName("DKK") val DKK: String? = null,
        @SerializedName("DOP") val DOP: String? = null,
        @SerializedName("DZD") val DZD: String? = null,
        @SerializedName("EGP") val EGP: String? = null,
        @SerializedName("ERN") val ERN: String? = null,
        @SerializedName("ETB") val ETB: String? = null,
        @SerializedName("EUR") val EUR: String? = null,
        @SerializedName("FJD") val FJD: String? = null,
        @SerializedName("FKP") val FKP: String? = null,
        @SerializedName("GBP") val GBP: String? = null,
        @SerializedName("GEL") val GEL: String? = null,
        @SerializedName("GGP") val GGP: String? = null,
        @SerializedName("GHS") val GHS: String? = null,
        @SerializedName("GIP") val GIP: String? = null,
        @SerializedName("GMD") val GMD: String? = null,
        @SerializedName("GNF") val GNF: String? = null,
        @SerializedName("GTQ") val GTQ: String? = null,
        @SerializedName("GYD") val GYD: String? = null,
        @SerializedName("HKD") val HKD: String? = null,
        @SerializedName("HNL") val HNL: String? = null,
        @SerializedName("HRK") val HRK: String? = null,
        @SerializedName("HTG") val HTG: String? = null,
        @SerializedName("HUF") val HUF: String? = null,
        @SerializedName("IDR") val IDR: String? = null,
        @SerializedName("ILS") val ILS: String? = null,
        @SerializedName("IMP") val IMP: String? = null,
        @SerializedName("INR") val INR: String? = null,
        @SerializedName("IQD") val IQD: String? = null,
        @SerializedName("IRR") val IRR: String? = null,
        @SerializedName("ISK") val ISK: String? = null,
        @SerializedName("JEP") val JEP: String? = null,
        @SerializedName("JMD") val JMD: String? = null,
        @SerializedName("JOD") val JOD: String? = null,
        @SerializedName("JPY") val JPY: String? = null,
        @SerializedName("KES") val KES: String? = null,
        @SerializedName("KGS") val KGS: String? = null,
        @SerializedName("KHR") val KHR: String? = null,
        @SerializedName("KMF") val KMF: String? = null,
        @SerializedName("KPW") val KPW: String? = null,
        @SerializedName("KRW") val KRW: String? = null,
        @SerializedName("KWD") val KWD: String? = null,
        @SerializedName("KYD") val KYD: String? = null,
        @SerializedName("KZT") val KZT: String? = null,
        @SerializedName("LAK") val LAK: String? = null,
        @SerializedName("LBP") val LBP: String? = null,
        @SerializedName("LKR") val LKR: String? = null,
        @SerializedName("LRD") val LRD: String? = null,
        @SerializedName("LSL") val LSL: String? = null,
        @SerializedName("LTL") val LTL: String? = null,
        @SerializedName("LVL") val LVL: String? = null,
        @SerializedName("LYD") val LYD: String? = null,
        @SerializedName("MAD") val MAD: String? = null,
        @SerializedName("MDL") val MDL: String? = null,
        @SerializedName("MGA") val MGA: String? = null,
        @SerializedName("MKD") val MKD: String? = null,
        @SerializedName("MMK") val MMK: String? = null,
        @SerializedName("MNT") val MNT: String? = null,
        @SerializedName("MOP") val MOP: String? = null,
        @SerializedName("MRU") val MRU: String? = null,
        @SerializedName("MUR") val MUR: String? = null,
        @SerializedName("MVR") val MVR: String? = null,
        @SerializedName("MWK") val MWK: String? = null,
        @SerializedName("MXN") val MXN: String? = null,
        @SerializedName("MYR") val MYR: String? = null,
        @SerializedName("MZN") val MZN: String? = null,
        @SerializedName("NAD") val NAD: String? = null,
        @SerializedName("NGN") val NGN: String? = null,
        @SerializedName("NIO") val NIO: String? = null,
        @SerializedName("NOK") val NOK: String? = null,
        @SerializedName("NPR") val NPR: String? = null,
        @SerializedName("NZD") val NZD: String? = null,
        @SerializedName("OMR") val OMR: String? = null,
        @SerializedName("PAB") val PAB: String? = null,
        @SerializedName("PEN") val PEN: String? = null,
        @SerializedName("PGK") val PGK: String? = null,
        @SerializedName("PHP") val PHP: String? = null,
        @SerializedName("PKR") val PKR: String? = null,
        @SerializedName("PLN") val PLN: String? = null,
        @SerializedName("PYG") val PYG: String? = null,
        @SerializedName("QAR") val QAR: String? = null,
        @SerializedName("RON") val RON: String? = null,
        @SerializedName("RSD") val RSD: String? = null,
        @SerializedName("RUB") val RUB: String? = null,
        @SerializedName("RWF") val RWF: String? = null,
        @SerializedName("SAR") val SAR: String? = null,
        @SerializedName("SBD") val SBD: String? = null,
        @SerializedName("SCR") val SCR: String? = null,
        @SerializedName("SDG") val SDG: String? = null,
        @SerializedName("SEK") val SEK: String? = null,
        @SerializedName("SGD") val SGD: String? = null,
        @SerializedName("SHP") val SHP: String? = null,
        @SerializedName("SLE") val SLE: String? = null,
        @SerializedName("SLL") val SLL: String? = null,
        @SerializedName("SOS") val SOS: String? = null,
        @SerializedName("SRD") val SRD: String? = null,
        @SerializedName("STD") val STD: String? = null,
        @SerializedName("SVC") val SVC: String? = null,
        @SerializedName("SYP") val SYP: String? = null,
        @SerializedName("SZL") val SZL: String? = null,
        @SerializedName("THB") val THB: String? = null,
        @SerializedName("TJS") val TJS: String? = null,
        @SerializedName("TMT") val TMT: String? = null,
        @SerializedName("TND") val TND: String? = null,
        @SerializedName("TOP") val TOP: String? = null,
        @SerializedName("TRY") val TRY: String? = null,
        @SerializedName("TTD") val TTD: String? = null,
        @SerializedName("TWD") val TWD: String? = null,
        @SerializedName("TZS") val TZS: String? = null,
        @SerializedName("UAH") val UAH: String? = null,
        @SerializedName("UGX") val UGX: String? = null,
        @SerializedName("USD") val USD: String? = null,
        @SerializedName("UYU") val UYU: String? = null,
        @SerializedName("UZS") val UZS: String? = null,
        @SerializedName("VEF") val VEF: String? = null,
        @SerializedName("VES") val VES: String? = null,
        @SerializedName("VND") val VND: String? = null,
        @SerializedName("VUV") val VUV: String? = null,
        @SerializedName("WST") val WST: String? = null,
        @SerializedName("XAF") val XAF: String? = null,
        @SerializedName("XAG") val XAG: String? = null,
        @SerializedName("XAU") val XAU: String? = null,
        @SerializedName("XCD") val XCD: String? = null,
        @SerializedName("XDR") val XDR: String? = null,
        @SerializedName("XOF") val XOF: String? = null,
        @SerializedName("XPF") val XPF: String? = null,
        @SerializedName("YER") val YER: String? = null,
        @SerializedName("ZAR") val ZAR: String? = null,
        @SerializedName("ZMK") val ZMK: String? = null,
        @SerializedName("ZMW") val ZMW: String? = null,
        @SerializedName("ZWL") val ZWL: String? = null
    )

    data class Error(
        @SerializedName("code") val code: String? = null,
        @SerializedName("message") val message: String? = null
    )
}
