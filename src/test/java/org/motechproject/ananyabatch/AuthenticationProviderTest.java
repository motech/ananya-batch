package org.motechproject.ananyabatch;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AuthenticationProviderTest {
    @Test
    public void shouldEncrypt() throws Exception {
        final String encrypt = new AuthenticationProvider().encrypt("test");
        Assert.assertEquals("CY9rzUYh03PK3k6DJie09g==", encrypt);
    }


}

