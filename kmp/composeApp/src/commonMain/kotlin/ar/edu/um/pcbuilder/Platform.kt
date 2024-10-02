package ar.edu.um.pcbuilder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform