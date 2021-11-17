package br.com.alura.forum.controller

import br.com.alura.forum.dto.DTopicRequest
import br.com.alura.forum.dto.DTopicResponse
import br.com.alura.forum.dto.DTopicUpdate
import br.com.alura.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topic")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list (): List<DTopicResponse> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById (@PathVariable id: Long): DTopicResponse {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun insert(@RequestBody @Valid topicRequest: DTopicRequest, uriBuilder: UriComponentsBuilder) : ResponseEntity<DTopicResponse>{
        val topicResponse = service.insert(topicRequest)
        val uri = uriBuilder.path("/topic/${topicResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicResponse)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid topicRequest: DTopicUpdate ) : ResponseEntity<DTopicResponse>{
        val topicResponse = service.update(topicRequest)
        return ResponseEntity.ok(topicResponse)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}