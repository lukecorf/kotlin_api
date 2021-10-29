package br.com.alura.forum.mapper

import br.com.alura.forum.domain.Topic
import br.com.alura.forum.dto.DTopicRequest
import br.com.alura.forum.dto.DTopicResponse
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicMapper(private val courseService: CourseService,
                  private val userService: UserService) :Mapper<Topic, DTopicResponse>{

    override fun map(t:Topic): DTopicResponse {
        return DTopicResponse(
            id = t.id,
            title = t.title,
            message = t.message,
            creationDate = t.creationDate,
            status = t.status
        )
    }

    fun mapEntity(t:DTopicRequest): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.idCourse),
            author = userService.findById(t.idAuthor)
        )
    }

}