package com.pajelonek.clipwatcher.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class User {

    @NonNull
    private String email;

    @NonNull
    private String password;

}