package com.anax.devops.application.usecase;

import com.anax.devops.domain.model.DevOpsMessage;
import com.anax.devops.domain.service.DevOpsService;
import org.springframework.stereotype.Service;

@Service
public class DevOpsProcessor implements DevOpsService {

    @Override
    public String processMessage(DevOpsMessage msg) {
        return "Hello " + msg.to() + " your message will be send";
    }
}