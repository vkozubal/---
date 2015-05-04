package org.pti.poster.service;

import org.pti.poster.model.Person;

import java.math.BigInteger;

public interface IPersonService {

    BigInteger addUser(final Person person);

    void remove(final BigInteger id);
}
