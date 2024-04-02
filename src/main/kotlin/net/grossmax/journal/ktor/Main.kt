package net.grossmax.journal.ktor

import com.sksamuel.hoplite.ConfigLoader
import net.grossmax.journal.ktor.dataaccess.config.AppConfig
import net.grossmax.journal.ktor.dataaccess.database.MariaDBDataSource
import net.grossmax.journal.ktor.userinterface.Webserver
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() {

    val config = ConfigLoader().loadConfigOrThrow<AppConfig>("config-env.yaml", "config-base.yaml")

    startKoin {
        val mainModule = module {
            single { config }
            single { config.webserver }
            single { config.database }
        }
        modules(mainModule)
    }

    MariaDBDataSource()
    Webserver()
}