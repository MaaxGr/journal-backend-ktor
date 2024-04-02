package net.grossmax.journal.ktor.userinterface

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import net.grossmax.journal.ktor.dataaccess.config.AppConfigWebserver
import net.grossmax.journal.ktor.dataaccess.database.dao.JournalEntryDao
import net.grossmax.journal.ktor.utils.inject

class Webserver {

    private val configWebserver: AppConfigWebserver by inject()
    private val journalEntryDao: JournalEntryDao by inject()

    init {

        embeddedServer(Netty, port = configWebserver.port) {

            install(ContentNegotiation) {
                json()
            }

            routing {
                get("/") {
                    val entries = journalEntryDao.selectAll()
                    call.respond(entries)
                }
            }
        }.start(true)

    }

}