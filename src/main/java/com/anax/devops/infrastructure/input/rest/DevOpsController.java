package com.anax.devops.infrastructure.input.rest;

import com.anax.devops.domain.model.DevOpsMessage;
import com.anax.devops.domain.service.DevOpsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {

    private final DevOpsService devOpsService;
    private final Set<String> processedJwts = ConcurrentHashMap.newKeySet();

    public DevOpsController(DevOpsService devOpsService) {
        this.devOpsService = devOpsService;
    }

    @PostMapping
    public ResponseEntity<?> handlePost(
            @RequestHeader("X-Parse-REST-API-Key") String apiKey,
            @RequestHeader("X-JWT-KWY") String jwt,
            @RequestBody DevOpsMessage request) {

        // Validar de API Key
        if (!"2f5ae96c-b558-4c7b-a590-a501ae1c3f6c".equals(apiKey)) {
            return ResponseEntity.status(403).body("ERROR");
        }

        // Validar JWT unico
        if (!processedJwts.add(jwt)) {
            return ResponseEntity.status(403).body("ERROR");
        }

        return ResponseEntity.ok(Map.of("message", devOpsService.processMessage(request)));
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> handleOthers() {
        return ResponseEntity.status(400).body("ERROR");
    }
}