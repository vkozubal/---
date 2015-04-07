package org.pti.poster.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import java.util.Arrays;
import java.util.Collection;

public final class Constants {

    public enum SECURITY {

        USER_ROLE("USER"),
        ADMIN_ROLE("ADMIN");
        public final String role;

        SECURITY(String role) {
            this.role = role;
        }

        public static Collection<String> getRolesAsString() {
            return Collections2.transform(Arrays.asList(SECURITY.values()), new Function<SECURITY, String>() {
                @Override
                public String apply(SECURITY security) {
                    return security.role;
                }
            });
        }

        public static String[] getAllRolesAsArray() {
            return getRolesAsString().toArray(new String[Constants.SECURITY.getRolesAsString().size()]);
        }
    }
}
