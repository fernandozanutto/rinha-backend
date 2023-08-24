package com.fzanutto.rinhabackend.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

@Table(name = "person")
data class PersonEntity(
    @Id
    @Column("id")
    @JsonProperty("id")
    var uuid: UUID = UUID.randomUUID(),

    @Column(value = "nickname")
    var apelido: String = "",

    @Column("name")
    var nome: String = "",

    @Column("birthday")
    @JsonFormat(pattern="yyyy-MM-dd")
    var nascimento: LocalDate = LocalDate.now(),

    @Column("stack")
    var stack: List<String>? = null,

    @JsonIgnore
    @Column("search")
    var search: String = "$apelido $nome ${stack?.joinToString("; ")}"
) : Serializable, Persistable<UUID> {
    @JsonIgnore
    override fun getId() = uuid

    @JsonIgnore
    override fun isNew() = true
}