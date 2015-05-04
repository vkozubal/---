package org.pti.poster.model;


import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class AbstractDocument {

    @Id
    private BigInteger documentId;

    public BigInteger getId() {
        return documentId;
    }

    public void setId(BigInteger id) {
        this.documentId = id;
    }

}

