package br.com.alura.forum.domain

import javax.persistence.*

@Entity
data class Users (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    val id: Long? = null,

    val name: String,

    val email: String
)