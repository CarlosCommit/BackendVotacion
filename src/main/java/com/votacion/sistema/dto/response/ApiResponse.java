package com.votacion.sistema.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ApiResponse implements Serializable {
    public Integer status;
    public String message;
    public Object payload;
    public String  error;
}
