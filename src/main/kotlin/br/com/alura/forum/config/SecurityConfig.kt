package br.com.alura.forum.config

import br.com.alura.forum.security.JWTAuthenticationFilter
import br.com.alura.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userDetailsService: UserDetailsService, private val jwtUtil: JWTUtil) : WebSecurityConfigurerAdapter() {


    /**
     * Any requests need authorization and i dont wanna use the spring login screen
     */
    override fun configure(http: HttpSecurity?) {
        http?.
        csrf()?.disable()?.
        authorizeRequests()?.
        antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        antMatchers(HttpMethod.GET, "/topic/**")?.hasAuthority("RULE_TOPICS_LIST")?.
        antMatchers(HttpMethod.PUT, "/topic")?.hasAuthority("RULE_TOPICS_SAVE")?.
        antMatchers(HttpMethod.POST, "/topic")?.hasAuthority("RULE_TOPICS_SAVE")?.
        antMatchers(HttpMethod.DELETE, "/topic/**")?.hasAuthority("RULE_TOPICS_DELETE")?.
        anyRequest()?.
        authenticated()?.
        and()
        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }

    /**
     * Encrypter for password
     */
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}