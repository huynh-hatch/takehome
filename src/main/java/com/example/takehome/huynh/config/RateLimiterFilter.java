package com.example.takehome.huynh.config;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.takehome.huynh.ratelimiter.RateLimter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huynh
 *
 *         Filter used to limit requests by user
 */
@Component
@Order(1)
@Slf4j
public class RateLimiterFilter implements Filter {

	
	private static final String H_XAUTHORIZATION = "X-Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// Check if user authorization token is in the requests
		String user = req.getHeader(H_XAUTHORIZATION);

		// Set default TPS limit
		int tpsLimit = 20;

		if (user == null) {

			// if guest/unauthorized user, set limit to 5, and set user to the ip address of user
			tpsLimit = 5;
			user = request.getRemoteAddr();
		}

		// Checks if the user's TPS is below the limit
		if (RateLimter.getInstance().isBelowLimit(user, tpsLimit)) {

			log.info("Processing: {}=>max {} TPS", user, tpsLimit);
			chain.doFilter(request, response);
		} else {
			// If above limit, do not process the other filters and return
			log.error("Blocked: {}=>max {} TPS reached", user, tpsLimit);
		}
	}
}