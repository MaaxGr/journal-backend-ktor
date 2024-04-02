package net.grossmax.journal.ktor.dataaccess.config


data class AppConfig(
    val webserver: AppConfigWebserver,
    val database: AppConfigDatabase
)

data class AppConfigWebserver(
    val port: Int
)

data class AppConfigDatabase(
    val hostname: String,
    val port: Int,
    val username: String,
    val password: String,
    val schema: String
)