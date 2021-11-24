package br.com.alura.forum.service

import br.com.alura.forum.domain.Users
import br.com.alura.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(private val repository: UserRepository) : UserDetailsService{

    fun findById(id: Long): Users {
        return repository.getById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username)?: throw RuntimeException()
        return UserDetail(user)
    }


}