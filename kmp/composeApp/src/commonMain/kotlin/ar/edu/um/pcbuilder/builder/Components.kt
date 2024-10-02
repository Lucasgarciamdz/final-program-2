package ar.edu.um.pcbuilder.builder

data class ComponentOption(
    val name: String,
    val price: Int
)


data class Chip(
    val name: String,      // The name of the chip (e.g., "Apple M1", "Intel i7")
    val cores: Int,        // The number of cores (e.g., 8, 16)
    val speedGhz: Double   // The speed of the processor in GHz
)

data class Memory(
    val sizeGb: Int,       // The size of memory in GB (e.g., 8, 16, 32)
    val type: String       // Type of memory (e.g., "DDR4", "LPDDR5")
)

data class Storage(
    val sizeGb: Int,       // The size of the storage in GB (e.g., 256, 512, 1024)
    val type: String       // Type of storage (e.g., "SSD", "HDD")
)

