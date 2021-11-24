package br.com.alura.forum.controller

import br.com.alura.forum.domain.Roles
import br.com.alura.forum.dto.DTopicReport
import br.com.alura.forum.dto.DTopicRequest
import br.com.alura.forum.dto.DTopicResponse
import br.com.alura.forum.dto.DTopicUpdate
import br.com.alura.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topic")
class TopicController(private val service: TopicService) {

    @GetMapping
    @Cacheable("topics")
    fun list (@RequestParam(required = false) courseName: String?, @PageableDefault(size = 5, sort = ["creationDate"], direction = Sort.Direction.DESC) pagination: Pageable): Page<DTopicResponse> {
        return service.findAll(courseName,pagination)
    }

    @GetMapping("/reports")
    fun report (): List<DTopicReport> {
        return service.report()
    }

    @GetMapping("/{id}")
    fun findById (@PathVariable id: Long): DTopicResponse {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    @Secured("RULE_TOPICS_LIST")
    fun insert(@RequestBody @Valid topicRequest: DTopicRequest, uriBuilder: UriComponentsBuilder) : ResponseEntity<DTopicResponse>{
        val topicResponse = service.insert(topicRequest)
        val uri = uriBuilder.path("/topic/${topicResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicResponse)
    }

    @PutMapping
    @Transactional
    @Secured("RULE_TOPICS_LIST")
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@RequestBody @Valid topicRequest: DTopicUpdate ) : ResponseEntity<DTopicResponse>{
        val topicResponse = service.update(topicRequest)
        return ResponseEntity.ok(topicResponse)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("RULE_TOPICS_DELETE")
    @CacheEvict(value = ["topics"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/{id}/roles")
    fun getRoles(@PathVariable id: Long): List<Roles> {
        return service.getRoles(id)
    }

}