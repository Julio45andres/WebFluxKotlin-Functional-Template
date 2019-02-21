package functional.users

import org.springframework.web.reactive.function.server.ServerResponse.created
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

class UserService(val users: UserRepository) {

    private var usersCache = mutableListOf(
        User("carmine", "d"),
        User("eliana", "g"),
        User("laura", "h")
    )

    fun findAll(): Flux<User> = this.users.findAll()

    fun create(user: User): Mono<User> =
        Mono.just(user)
            .flatMap { user -> this.users.save(user) }


    fun asUpperCase(): Flux<User> = Flux.fromIterable(usersCache.map {
        User(
            it.firstname.toUpperCase(),
            it.lastname.toUpperCase()
        )
    })
}