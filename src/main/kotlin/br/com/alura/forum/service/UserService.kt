package br.com.alura.forum.service

import br.com.alura.forum.domain.Users
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun findById(id: Long): Users {
        return repository.getById(id)
    }
}