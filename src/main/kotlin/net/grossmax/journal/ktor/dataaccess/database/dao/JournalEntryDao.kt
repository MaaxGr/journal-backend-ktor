package net.grossmax.journal.ktor.dataaccess.database.dao

import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.serialization.Serializable
import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.Instant
import java.time.LocalDate

interface JournalEntryDao {

    @Serializable
    data class JournalEntry(
        val id: Int,
        val title: String,
        val content: String,
        val targetDate: kotlinx.datetime.LocalDate,
        val createdAt: kotlinx.datetime.Instant
    )

    fun selectAll(): List<JournalEntry>
}

class JournalEntryDaoImpl(private val database: Database): JournalEntryDao {

    override fun selectAll(): List<JournalEntryDao.JournalEntry> {
        return database.sequenceOf(JournalEntryTable).map { it.toEntry() }
    }

    object JournalEntryTable: Table<JournalEntryEntity>("journal_entry") {
        val id = int("id").primaryKey().bindTo { it.id }
        val title = varchar("title").bindTo { it.title }
        val content = varchar("content").bindTo { it.content }
        val targetDate = date("target_date").bindTo { it.targetDate }
        val createdAt = timestamp("created_at").bindTo { it.createdAt }
    }

    interface JournalEntryEntity: Entity<JournalEntryEntity> {
        companion object : Entity.Factory<JournalEntryEntity>()

        val id: Int
        val title: String
        val content: String
        val targetDate: LocalDate
        val createdAt: Instant

        fun toEntry() = JournalEntryDao.JournalEntry(
            id = id,
            title = title,
            content = content,
            targetDate = targetDate.toKotlinLocalDate(),
            createdAt = createdAt.toKotlinInstant()
        )

    }

}