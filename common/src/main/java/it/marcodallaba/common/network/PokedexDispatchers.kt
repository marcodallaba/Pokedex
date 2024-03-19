package it.marcodallaba.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val pokedexDispatcher: PokedexDispatchers)

enum class PokedexDispatchers {
    Default,
    IO,
}
