package com.project.questaidbackend.models.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class GeneralResponseData {
    private String key;
    private Object value;
}
