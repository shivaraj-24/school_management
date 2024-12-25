package com.cts.SchoolManagementSystem.entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ErrorClass {

    private HttpStatus status;
    private String msg;

    public ErrorClass(){

    }

    public ErrorClass(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
