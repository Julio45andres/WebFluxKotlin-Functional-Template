package functional.users

import com.mongodb.ConnectionString
import org.springframework.context.support.beans
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory

fun userBeans() = beans {
    bean<UserHandler>()
    bean<UserService>()
    bean<UserRepository>()
    bean<UserRoutes>()
}