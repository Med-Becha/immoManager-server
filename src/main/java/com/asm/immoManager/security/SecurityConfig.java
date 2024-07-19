// package com.asm.immoManager.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// BCryptPasswordEncoder bCryptPasswordEncoder;

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .csrf(csrf -> csrf.disable())
// .authorizeHttpRequests(authorize -> authorize
// .anyRequest().permitAll())
// .formLogin(form -> form.defaultSuccessUrl("/**", true)
// .permitAll());

// http.sessionManagement(management ->
// management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
// return http.build();
// }

// @Bean
// public UserDetailsService users() {
// UserDetails admin = User.builder().username("admin")
// .password(bCryptPasswordEncoder.encode("admin-pass"))
// .roles("ADMIN")
// .build();
// UserDetails user = User.builder().username("user")
// .password(bCryptPasswordEncoder.encode("user-pass"))
// .roles("USER")
// .build();
// return new InMemoryUserDetailsManager(admin, user);
// }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .cors(AbstractHttpConfigurer::disable)
// .csrf(AbstractHttpConfigurer::disable)
// .sessionManagement(session -> session
// .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authorizeHttpRequests(auth -> auth
// .requestMatchers("/user/login", "/user").permitAll()
// .anyRequest().authenticated());

// return http.build();
// }

// @Bean
// public CorsFilter corsFilter() {
// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// CorsConfiguration config = new CorsConfiguration();
// config.setAllowCredentials(true);
// config.addAllowedOrigin("*"); // Adjust the allowed origin as needed
// config.addAllowedHeader("*");
// config.addAllowedMethod("*");
// source.registerCorsConfiguration("/**", config);
// return new CorsFilter(source);
// }
// }
