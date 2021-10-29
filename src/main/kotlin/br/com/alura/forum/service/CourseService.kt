package br.com.alura.forum.service

import br.com.alura.forum.domain.Course
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService(var courses: List<Course>) {
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "test"
        )

        courses = listOf(course)
    }

    fun findById(id: Long): Course {
        return courses.stream().filter { c -> c.id == id }.findFirst().get()
    }
}