package it.marcodallaba.network.util

import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry
import it.marcodallaba.model.Stat
import it.marcodallaba.network.dto.PokemonInfoResponse
import it.marcodallaba.network.dto.PokemonListResponse

fun PokemonListResponse.toModel(): List<PokemonListEntry> {
    return this.results.map { entry ->
        val number = if (entry.url.endsWith("/")) {
            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            entry.url.takeLastWhile { it.isDigit() }
        }
        val imageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

        PokemonListEntry(
            name = entry.name,
            imageUrl = imageUrl,
            number = number
        )
    }
}

fun PokemonInfoResponse.toModel(): PokemonInfo {
    return PokemonInfo(
        id = this.id,
        name = this.name,
        frontImageDefaultUrl = this.sprites.frontDefault,
        types = this.types.map {
            it.type.name
        },
        stats = this.stats.map {
            Stat(
                name = it.stat.name,
                baseStat = it.baseStat
            )
        },
        height = this.height,
        weight = this.weight
    )
}
