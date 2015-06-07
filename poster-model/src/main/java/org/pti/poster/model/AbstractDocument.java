package org.pti.poster.model;


import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

@EqualsAndHashCode
public class AbstractDocument implements Serializable{

    @Id
    private BigInteger documentId;

    public BigInteger getId() {
        return documentId;
    }

    public void setId(BigInteger id) {
        this.documentId = id;
    }
}

