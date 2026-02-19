package com.com.mediaalterations.gateway;

import org.springframework.cloud.gateway.server.mvc.filter.FilterSupplier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

@Component
public class AuthFilterSupplier implements FilterSupplier{

    @Override
    public Collection<Method> get() {
        return Arrays.asList(AuthenticationFilter.class.getMethods());
    }
}
