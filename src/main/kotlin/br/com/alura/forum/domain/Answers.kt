package br.com.alura.forum.domain

import java.time.LocalDateTime

data class Answers (
    val id: Long? = null,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val solved: Boolean
)