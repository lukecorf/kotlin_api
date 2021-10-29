package br.com.alura.forum.dto

import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime

data class DTopicResponse (
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val creationDate: LocalDateTime
)