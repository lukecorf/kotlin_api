package br.com.alura.forum.domain

import javax.persistence.*

@Entity
class Course (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COURSE")
    @SequenceGenerator(name = "SEQ_COURSE", sequenceName = "SEQ_COURSE", allocationSize = 1)
    val id: Long? = null,

    val name: String,

    val category: String
)