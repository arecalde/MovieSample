package com.example.moviesample.model

import com.google.gson.annotations.SerializedName

data class Result (

    @SerializedName("dates"         ) var dates        : Dates?             = Dates(),
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null

)

data class Dates (

    @SerializedName("maximum" ) var maximum : String? = null,
    @SerializedName("minimum" ) var minimum : String? = null

)