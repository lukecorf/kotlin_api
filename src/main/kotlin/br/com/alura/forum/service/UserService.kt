package br.com.alura.forum.service

import br.com.alura.forum.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(var users: List<User>) {

    init {
      val user = User(
          id = 1,
          name = "Luke Skywalker",
          email = "luke@email.com"
      )

      users = listOf(user);
    }

    fun findById(id: Long): User {
        return users.stream().filter { c -> c.id == id }.findFirst().get()
    }
}