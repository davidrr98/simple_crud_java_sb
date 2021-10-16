package com.david.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health_check")
public class HealthCheckREST {
	@GetMapping
	private ResponseEntity<Void> healthCheck(){
		return ResponseEntity.ok(null);
	}
}
