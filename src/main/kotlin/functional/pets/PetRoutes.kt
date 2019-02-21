package functional.pets

import functional.common.internalServerError
import functional.pets.PetHandler
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.RouterFunctionDsl
import org.springframework.web.reactive.function.server.router
import java.net.URI

class PetRoutes(private val petHandler: PetHandler) {

    fun getRouting(): RouterFunctionDsl.() -> Unit  {
        return {
                accept(TEXT_HTML).nest {
                GET("/") { permanentRedirect(URI("index.html")).build() }
            }
            "/api".nest {
                accept(APPLICATION_JSON).nest {
                    GET("/pets", petHandler::findAll)
                    POST("/pets", petHandler::create)
                }
            }
            resources("/**", ClassPathResource("static/"))
        }
    }
}