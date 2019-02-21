package functional.pets

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class PetService(val pets: PetsRepository) {

    fun findAll(): Flux<Pet> = pets.findAll()
    fun save(pet: Pet): Mono<Pet> = pets.save(pet)
}