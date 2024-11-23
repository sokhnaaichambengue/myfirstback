package isepddiamniadio.pff.Gestion_performance.service;

import java.util.Date;
import java.util.Optional;

import isepddiamniadio.pff.Gestion_performance.dao.UserTokenRepository;
import isepddiamniadio.pff.Gestion_performance.entities.User;
import isepddiamniadio.pff.Gestion_performance.entities.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
    public class UserTokenService {

        private static  final String DICO="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm123456789";

        private static final Integer TOKEN_LENGTH = 144;

        @Autowired
      private UserTokenRepository userTokenRepository;

        @Autowired
        private UserService userService;

        public void save(UserToken userToken) {
            userTokenRepository.save(userToken);
        }

        public Optional<UserToken> findByToken(String token) {
            return userTokenRepository.findById(token);
        }

        public UserToken generateUserToken(User user){
            UserToken userToken = new UserToken();
            userToken.setUser(user);
            userToken.setToken(newToken());
            Date now = new Date();
            int nbMilliSeconds = 10*60*60*1000;
            Date expiryDate = new Date(now.getTime() + nbMilliSeconds);
            userToken.setNotBefore(now);
            userToken.setNotAfter(expiryDate);
            userTokenRepository.save(userToken);
            return userToken;

        }

        public String newToken(){

            Optional<UserToken>  userToken;
            String token="";
            do{
                token="";
                for(int i=0;i<TOKEN_LENGTH;i++){
                    int position=(int)(Math.random()*DICO.length());
                    token+=DICO.charAt(position);
                }
                userToken= userTokenRepository.findById(token);
            } while(userToken.isPresent());
            return token;
        }

        public UserToken login(String username, String password){
            Optional<User> user= userService.findByEmail(username);
            if(user.isPresent()){
                if(user.get().getPassword().equals(password)){
                    UserToken res= generateUserToken(user.get());
                    return res;
                }
            }
            throw new UsernameNotFoundException("Login ou mot de pass incorrecte.");
        }
    }

