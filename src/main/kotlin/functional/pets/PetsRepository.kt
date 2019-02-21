package functional.pets

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class PetsRepository(private val template: ReactiveMongoTemplate) {
    fun findAll(): Flux<Pet> = template.findAll(Pet::class.java)
    fun findByName(name: String): Flux<Pet> = template.find(Query(Criteria.where("name").`is`(name)), Pet::class.java)
    fun save(pet: Pet): Mono<Pet> = template.save(pet)
}