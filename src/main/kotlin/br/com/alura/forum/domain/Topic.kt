package br.com.alura.forum.domain

import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime

data class Topic (
    val id: Long? = null,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.WITHOUT_RESPONSE,
    val answers: List<Answers> = ArrayList()
)