package functional.common

import com.mongodb.ConnectionString
import functional.common.internalServerError
import functional.pets.PetRoutes
import functional.users.UserRoutes
import org.springframework.context.support.beans
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.adapter.WebHttpHandlerBuilder

fun routerBeans() = beans {
    bean(WebHttpHandlerBuilder.WEB_HANDLER_BEAN_NAME) {
        RouterFunctions.toWebHandler(
            router {
                ref<UserRoutes>().getRouting().invoke(this)
                ref<PetRoutes>().getRouting()()
            }.filter { request, next ->
                try {
                    next.handle(request)
                } catch (ex: Exception) {
                    internalServerError(ex)
                }
            }
        )
    }
}