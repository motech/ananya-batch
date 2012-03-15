package org.motechproject.tamabatch;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AuthenticationProviderTest {
    @Test
    public void shouldEncrypt() throws Exception {
        final String encrypt = new AuthenticationProvider().encrypt("test");
        assertEquals("CY9rzUYh03PK3k6DJie09g==", encrypt);
    }


}

