package br.com.alura.forum.service

import br.com.alura.forum.domain.Course
import br.com.alura.forum.domain.Topic
import br.com.alura.forum.domain.User
import org.springframework.stereotype.Service

@Service
class TopicService {

    fun findAll(): List<Topic> {
        val topic = Topic(
            id = 1,
            title = "Question Kotlin",
            message = "Variables in Kotlin",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programming"
            ),
            author = User(
                id = 1,
                name = "Luke Skywalker 2",
                email = "luke@resistence.com"
            )
        )
        return listOf(topic,topic)
    }
}