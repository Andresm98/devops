package com.anax.devops.domain.model;

public record DevOpsMessage(String message, String to, String from, int timeToLifeSec) {}
