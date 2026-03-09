package com.anax.devops.domain.service;

import com.anax.devops.domain.model.DevOpsMessage;

public interface DevOpsService {
    String processMessage(DevOpsMessage message);
}