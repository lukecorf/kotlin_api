package br.com.alura.forum.service

import br.com.alura.forum.domain.Course
import br.com.alura.forum.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun findById(id: Long): Course {
        return repository.getById(id)
    }
}