package com.example.seurityauthentication.config;

import com.example.seurityauthentication.service.JwtService;
import com.example.seurityauthentication.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String authHeader = request.getHeader("Authorization");
     String token=null;
     String userName=null;
     if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        System.out.println("token:"+token);
        userName=jwtService.extractUserName(token);
        System.out.println("userName:"+userName);
     }
     if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){

         UserDetails userDetails=context.getBean(MyUserDetailService.class).loadUserByUsername(userName);
         if(jwtService.validateToken(token, userDetails)){

            UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(userDetails,null);
            authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenToken);
            System.out.println("authenToken:"+SecurityContextHolder.getContext().getAuthentication());
         }
     }
     filterChain.doFilter(request,response);
    }
}
