package it.marcodallaba.data.repository

import it.marcodallaba.model.PokemonListEntry
import java.util.concurrent.atomic.AtomicInteger

class PokemonListEntryFactory {
    private val counter = AtomicInteger(0)
    fun createPokemon(): PokemonListEntry {
        val id = counter.incrementAndGet()
        val pokemon = PokemonListEntry(
            name = "name_$id",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
            number = "$id",
        )
        return pokemon
    }
}