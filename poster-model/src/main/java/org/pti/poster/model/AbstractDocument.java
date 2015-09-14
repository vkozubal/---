package org.pti.poster.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;
import java.math.BigInteger;

@EqualsAndHashCode
public class AbstractDocument implements Serializable, Identifiable<BigInteger> {

    @Id @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}

