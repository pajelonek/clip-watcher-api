package com.pajelonek.clipwatcher.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Builder
public class RegistrationResponse {
    @NonNull
    private String status;
}
