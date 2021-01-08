package org.example.dto;

import javax.validation.constraints.NotEmpty;

public class AdminDTO {
    @NotEmpty
    private String name;

    public AdminDTO() {
    }

    public AdminDTO(@NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
