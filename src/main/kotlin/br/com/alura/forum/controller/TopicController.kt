package br.com.alura.forum.controller

import br.com.alura.forum.domain.Course
import br.com.alura.forum.domain.Topic
import br.com.alura.forum.domain.User
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topic")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list (): List<Topic> {
        return service.findAll()
    }

}