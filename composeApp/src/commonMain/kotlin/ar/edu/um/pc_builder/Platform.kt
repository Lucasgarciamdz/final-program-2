package ar.edu.um.pc_builder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform