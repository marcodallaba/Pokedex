package it.marcodallaba.network.dto


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)