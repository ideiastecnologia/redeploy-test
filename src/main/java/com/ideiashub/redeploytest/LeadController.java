package com.ideiashub.redeploytest;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadRepository repository;

    public LeadController(LeadRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Lead> list() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping
    public Lead create(@RequestBody Map<String, String> body) {
        Lead lead = new Lead();
        lead.setName(body.getOrDefault("name", ""));
        lead.setEmail(body.getOrDefault("email", ""));
        lead.setMessage(body.getOrDefault("message", ""));
        return repository.save(lead);
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        return Map.of("total", repository.count());
    }
}
