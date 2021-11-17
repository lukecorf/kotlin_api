package br.com.alura.forum.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Answers (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANSWERS")
    @SequenceGenerator(name = "SEQ_ANSWERS", sequenceName = "SEQ_ANSWERS", allocationSize = 1)
    val id: Long? = null,

    val message: String,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val author: Users,

    @ManyToOne
    val topic: Topic,

    val solved: Boolean
)