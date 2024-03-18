package it.marcodallaba.model

data class PokemonInfo(
    val id: Int,
    val name: String,
    val frontImageDefaultUrl: String,
    val types: List<String>,
    val stats: List<Stat>,
    val height: Int,
    val weight: Int,
)

data class Stat(
    val name: String,
    val baseStat: Int,
)
