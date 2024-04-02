package com.pfe13.dto;


public record UserDto(
    String id,
    String password,
    String name,
    String email,
    String role) {


}
