package com.app.test.utils;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
    private boolean status = false;
    private String message = "";
    private String error = null;
    private Object data = null;  
    
}
