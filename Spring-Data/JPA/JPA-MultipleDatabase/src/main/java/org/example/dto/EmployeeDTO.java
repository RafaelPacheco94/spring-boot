package org.example.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeDTO {

    @NotEmpty
    private String name;

    public EmployeeDTO() {
    }

    public EmployeeDTO(@NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
