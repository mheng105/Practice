package com.vmo.training.demo.models.assignment2a;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectInfo {
    private Integer id;
    private Object name;
    private Integer color;
    private boolean favorite;

    public ProjectInfo() {
    }
}
