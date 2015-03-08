package org.pti.poster.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Person {

    @Id
    private String personId;
    private String userName;
}
