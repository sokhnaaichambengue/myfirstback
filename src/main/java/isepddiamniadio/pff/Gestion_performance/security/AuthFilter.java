package isepddiamniadio.pff.Gestion_performance.security;

import isepddiamniadio.pff.Gestion_performance.entities.Role;
import isepddiamniadio.pff.Gestion_performance.entities.User;
import isepddiamniadio.pff.Gestion_performance.entities.UserToken;
import isepddiamniadio.pff.Gestion_performance.service.UserService;
import isepddiamniadio.pff.Gestion_performance.service.UserTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;  // Pour récupérer les utilisateurs et leurs rôles

    @Autowired
    private UserTokenService userTokenService;  // Pour valider les tokens JWT

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Extraire le token

            // Valider le token et récupérer l'utilisateur associé
            UserToken userToken = userTokenService.findByToken(token).orElse(null);
            if (userToken != null && userToken.getNotAfter().after(new Date())) {
                User user = userToken.getUser();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                // Ajouter les rôles de l'utilisateur dans les autorités
                for (Role role : user.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode())); // Assurez-vous que les rôles sont bien formatés
                }

                // Authentifier l'utilisateur avec les rôles
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);  // Laisser passer la requête
    }


    }

