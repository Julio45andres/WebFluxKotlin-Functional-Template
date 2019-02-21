package functional.users

import functional.common.internalServerError
import functional.users.UserHandler
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import java.net.URI

class UserRoutes(private val userHandler: UserHandler) {

    fun getRouting(): RouterFunctionDsl.() -> Unit {
        return {
            accept(TEXT_HTML).nest {
                GET("/") { permanentRedirect(URI("index.html")).build() }
            }
            "/api".nest {
                accept(APPLICATION_JSON).nest {
                    GET("/users", userHandler::findAll)
                    POST("/users", userHandler::create)
                    "/users".nest {
                        GET("/uppercase", userHandler::asUpperCase)
                    }
                }
            }
            resources("/**", ClassPathResource("static/"))
        }
    }
}
//.filter { request, next ->
//    try {
//        next.handle(request)
//    } catch (ex: Exception) {
//        internalServerError(ex)
//    }
//}