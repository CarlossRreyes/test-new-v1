package com.testauth.utils;


import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
    private boolean status = false;
    private String message = "";
    private String token = null;
}
