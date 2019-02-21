package functional.pets

import org.springframework.context.support.beans

fun petBeans() = beans {
    bean<PetHandler>()
    bean<PetsRepository>()
    bean<PetService>()
    bean<PetRoutes>()
}