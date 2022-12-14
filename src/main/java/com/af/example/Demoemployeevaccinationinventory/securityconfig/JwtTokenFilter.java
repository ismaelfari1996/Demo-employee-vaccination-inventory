/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.securityconfig;

import com.af.example.Demoemployeevaccinationinventory.service.UserDetailsImplements;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Ismael
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private UserDetailsImplements userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           String token=getToken(request);
          /* if(token!=null && jwtProvider.checkToken(token).isEmpty()){
                String userName=jwtProvider.getUserNameFromToken(token);
               UserDetails userDetails=userDetailService.loadUserByUsername(userName);
               UsernamePasswordAuthenticationToken auth=
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);
           }else{
              logger.error( jwtProvider.checkToken(token).get("ERROR"));
           }*/
           if(token!=null && jwtProvider.validateToken(token)){
               String userName=jwtProvider.getUserNameFromToken(token);
               UserDetails userDetails=userDetailService.loadUserByUsername(userName);
               UsernamePasswordAuthenticationToken auth=
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);
           }
              
           
           
       }catch(Exception e){
           logger.error("Error en el metodo doFilter");
       }
       
       filterChain.doFilter(request, response);
    }
    
    private String getToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            return header.replace("Bearer ", "");
        }
        return null;
    }
    
}
