package net.grossmax.journal.ktor.dataaccess.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.grossmax.journal.ktor.dataaccess.config.AppConfigDatabase
import net.grossmax.journal.ktor.dataaccess.database.dao.JournalEntryDao
import net.grossmax.journal.ktor.dataaccess.database.dao.JournalEntryDaoImpl
import net.grossmax.journal.ktor.utils.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.ktorm.database.Database

class MariaDBDataSource {

    private val configDatabase: AppConfigDatabase by inject()

    init {
        val config = HikariConfig().apply {
            driverClassName = "org.mariadb.jdbc.Driver"
            jdbcUrl = "jdbc:mariadb://${configDatabase.hostname}:${configDatabase.port}/${configDatabase.schema}"
            username = configDatabase.username
            password = configDatabase.password
        }

        val dataSource = HikariDataSource(config)
        val ktormDatabase = Database.connect(dataSource)


        val daoModule = module {
            single<JournalEntryDao> { JournalEntryDaoImpl(ktormDatabase) }
        }
        loadKoinModules(daoModule)
    }

}