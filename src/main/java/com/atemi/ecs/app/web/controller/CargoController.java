package com.atemi.ecs.app.web.controller;

import com.atemi.ecs.app.web.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/send/{country}")
    public String sendCargo(@PathVariable String country) {
        log.info("Received request to send cargo to {}", country);
        try {
            deliveryService.deliverCargo(country);
            return String.format("Started cargo delivery to %s", country);
        } catch (Exception e) {
            log.error("Error occurred", e);
            return String.format("Failed to deliver cargo to %s", country);
        }
    }
}
