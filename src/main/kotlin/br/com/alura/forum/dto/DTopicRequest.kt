package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class DTopicRequest (

    @field:NotEmpty(message = "Title cannot be empty")
    @field:Size(min =5, max = 100, message = "Title need have size between 5 and 100")
    val title: String,
    @field:NotEmpty(message = "Message cannot be empty")
    val message: String,
    @field:NotNull(message = "Course ID cannot be null")
    val idCourse: Long,
    @field:NotNull(message = "Author ID cannot be null")
    val idAuthor: Long
)
