//package tp_group1.spring_boot_pokemon.security;
//
//import tp_group1.spring_boot_pokemon.service.TrainerService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * Classe de configuration de Spring Security
// */
//@Configuration
//public class SecurityConfiguration {
//
//    private final TrainerService trainerService;
//
//    public SecurityConfiguration(TrainerService trainerService) {
//        this.trainerService = trainerService;
//    }
//
//    /**
//     * Configure les URLs et la sécurité associée à chacune d'entre elles
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Réutilisation de HttpSecurity fourni par SpringSecurity
//        http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests -> requests
//                        // les URLs ci-dessous peuvent être requêtées par tout le monde
//                        .requestMatchers("/", "/home", "/login", "/error", "/logout", "/managers/**", "/teams/**", "/css/**", "/js/**").permitAll()
//                        // Toute autre requête ne peut être émise que par une personne authentifiée
//                        .anyRequest().authenticated())
//                // la page de login est accessible via /login et est accessible par tout le monde
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/", true) // Redirection après connexion réussie
//                        .permitAll())
//                // La page de logout est aussi accessible par tout le monde
//                .logout(logout -> logout.permitAll())
//                // L'utilisation de ManagerService pour récupérer les utilisateurs
//                .userDetailsService(managerService);
//
//        return http.build();
//    }
//
//    /**
//     * Bean pour l'encodeur de mot de passe BCrypt.
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
