rootProject.name = "hcbox"
include(":api")
include(":common")
include("services")
include("services:product-service")
findProject(":services:product-service")?.name = "product-service"
include("services:member-service")
findProject(":services:member-service")?.name = "member-service"
include("services:order-service")
findProject(":services:order-service")?.name = "order-service"
