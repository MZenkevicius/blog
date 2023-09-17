package com.mariusblog.blogas.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class MyErrorResolver implements ErrorViewResolver {

    /**
     * resolveErrorView - can be used to route user to specific error view
     * @param request - request to error page (processed by Spring)
     * @param status - spring resolved http response status
     * @param model - some unmodifiable map, provided by Spring and containing basic info about the error (timestamp, status, etc..)
     * @return
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        model = new HashMap<>(model);
        model.put("errorId", UUID.randomUUID().toString());

        ModelAndView modelAndView;
        switch (status) {
            case NOT_FOUND:
                modelAndView = new ModelAndView("error/404", model);
                break;
            default:
                modelAndView = new ModelAndView("error", model);
                break;
        }
        return modelAndView;
    }
}
