package com.smart.smartApi.dto;

import lombok.Builder;

@Builder
public record MailBodyDto(String to, String subject, String text) {
}