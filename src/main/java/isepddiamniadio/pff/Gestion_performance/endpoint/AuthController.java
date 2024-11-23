package isepddiamniadio.pff.Gestion_performance.endpoint;

import isepddiamniadio.pff.Gestion_performance.dto.AuthRequest;
import isepddiamniadio.pff.Gestion_performance.dto.AuthResponse;
import isepddiamniadio.pff.Gestion_performance.entities.User;
import isepddiamniadio.pff.Gestion_performance.entities.UserToken;
import isepddiamniadio.pff.Gestion_performance.service.UserService;
import isepddiamniadio.pff.Gestion_performance.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserTokenService userTokenService;

    private final UserService userService;

    @Autowired
    public AuthController(UserTokenService userTokenService, UserService userService) {
        this.userTokenService = userTokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // Valider l'utilisateur et générer un token
        UserToken userToken = userTokenService.login(authRequest.getUsername(), authRequest.getPassword());

        // Retourner le token dans la réponse
        return ResponseEntity.ok(new AuthResponse(userToken.getToken()));  // Retourne uniquement le token
    }

    @PostMapping("/inscrire")
    public ResponseEntity<?> inscrire(@RequestBody User user) {
        if (user.getPrenom() == null || user.getNom() == null || user.getNom().isBlank() || user.getPrenom().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        userService.save(user);
        return ResponseEntity.status(201).build();
    }
}
