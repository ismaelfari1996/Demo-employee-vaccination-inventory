/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.securityconfig;

import com.af.example.Demoemployeevaccinationinventory.model.MainUser;
import com.af.example.Demoemployeevaccinationinventory.utility.TokenError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ismael
 */

@Component
public class JwtProvider {
   
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expirationTime}")
    private int expirationTime;
    
    private static final Logger logger= LoggerFactory.getLogger(JwtProvider.class);
    
    public String generateToken(Authentication authentication){
        MainUser mainUser=(MainUser)authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expirationTime*1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token){
        try{
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
        }catch(MalformedJwtException e){
            TokenError.setTokenError("ERROR","Error de formato de token");
            logger.error("Error de formato de token");
        }catch(UnsupportedJwtException e){
            TokenError.setTokenError("ERROR", "Token no soportado");
            logger.error("Token no soportado");
        }catch(ExpiredJwtException e){
            TokenError.setTokenError("ERROR", "Token expirado, vuelva a iniciar sesion");
            logger.error("Token expirado");
            
        }catch(IllegalArgumentException e){
            TokenError.setTokenError("ERROR", "Token vacio");
            logger.error("Token vacio");
        }catch(SignatureException e){
            TokenError.setTokenError("ERROR", "Firma invalida");
            logger.error("Firma invalido");
        }
        return false;
    }
    
    
    public HashMap<String,String> checkToken(String token){
        HashMap<String , String > error=new HashMap<>();
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        }catch(MalformedJwtException e){
            error.put("ERROR", "Error de formato en el token");
        }catch(UnsupportedJwtException e){
            error.put("ERROR", "Token no soportado");
        }catch(ExpiredJwtException e){
            error.put("ERROR", "Token expirado, vuelva a iniciar sesion");
        }catch(IllegalArgumentException e){
            error.put("ERROR", "Token vacio");
        }catch(SignatureException e){
            error.put("ERROR", "Firma invalida");
        }
        return error;
    }
}
