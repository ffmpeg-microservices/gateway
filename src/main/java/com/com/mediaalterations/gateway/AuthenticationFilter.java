package com.com.mediaalterations.gateway;

import org.springframework.cloud.gateway.server.mvc.common.Shortcut;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

public class AuthenticationFilter{


    @Shortcut("role") // Maps the first property value to the 'role' parameter
    public static HandlerFilterFunction<ServerResponse, ServerResponse>
        authenticationFilter(String role){
        return (request, next) -> {
            List<String> values = request.headers().asHttpHeaders().get(HttpHeaders.AUTHORIZATION);

            if(values == null || !values.getFirst().startsWith("Bearer")) {
                throw new UnauthorizedAccessException("Unauthorized Access. Login or Signup");
            }

            String token = values.getFirst().split("Bearer ")[1];

            if(JwtUtil.isValid(token)){
                String userId = JwtUtil.getUserId(token);
                if(userId != null){
                    ServerRequest newRequest = ServerRequest.from(request)
                            .header("user_id",userId)
                            .build();
                    return next.handle(newRequest);
                }
            }

            throw new UnauthorizedAccessException("Unauthorized Access. Login or Signup");
        };
    }
}
