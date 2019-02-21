package functional.common

import com.mongodb.ConnectionString
import functional.pets.PetRoutes
import functional.users.UserRoutes
import functional.pets.PetHandler
import functional.pets.PetService
import functional.pets.PetsRepository
import functional.users.UserHandler
import functional.users.UserRepository
import functional.users.UserService
import org.springframework.context.support.beans
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.adapter.WebHttpHandlerBuilder

fun dbBeans() = beans {
    bean {
        val connectionString = "mongodb://localhost:27017/blog"
        ReactiveMongoTemplate(
            SimpleReactiveMongoDatabaseFactory(
                ConnectionString(connectionString)
            )
        )
    }
}