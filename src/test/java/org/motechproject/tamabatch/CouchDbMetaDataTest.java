package org.motechproject.tamabatch;

import org.junit.Test;
import org.motechproject.tamabatch.couchdb.CouchDbMetaData;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CouchDbMetaDataTest {

    @Test
    public void shouldGetDesignDocs() throws Exception {
        final List<String> designDocNames = new CouchDbMetaData().getDesignDocNames("tama-web");
        assertTrue(designDocNames.size() > 0);
    }

    @Test
    public void shouldGetListOfDatabases() throws Exception {
        List<String> dbList = CouchDbMetaData.getApplicationDatabases();
        assertTrue(dbList.size()>0);
    }
}

