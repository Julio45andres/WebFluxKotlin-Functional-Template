package functional.pets

import functional.common.validate
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono


class PetHandler(val petService: PetService) {

    fun findAll(req: ServerRequest): Mono<ServerResponse> = validate
        .request(req) {
            ok().body(petService.findAll())
        }

    fun create(req: ServerRequest): Mono<ServerResponse> = validate
        .request(req)
        .withBody(Pet::class.java) { pet ->
            ok().body(petService.save(pet))
        }
}