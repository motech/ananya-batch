package org.motechproject.tamabatch;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CouchDbMetaDataTest {

    @org.junit.Test
    public void shouldGetDesignDocs() throws Exception {
        final List<String> designDocNames = new CouchDbMetaData("tama-web").getDesignDocNames();
        assertTrue(designDocNames.size() > 0);
    }
}
