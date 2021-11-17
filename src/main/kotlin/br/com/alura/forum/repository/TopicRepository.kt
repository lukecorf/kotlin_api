package br.com.alura.forum.repository

import br.com.alura.forum.domain.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
}