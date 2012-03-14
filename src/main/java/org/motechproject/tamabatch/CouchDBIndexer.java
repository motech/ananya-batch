package org.motechproject.tamabatch;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CouchDBIndexer {
    private String dbName;

    public CouchDBIndexer(String dbName) {
        this.dbName = dbName;
    }

    public void indexAllViews() throws IOException, JSONException {
        final List<String> designDocNames = new CouchDbMetaData("tama-web").getDesignDocNames();
        for (String designDocName : designDocNames) {
            indexView(designDocName);
        }
    }

    private void indexView(String designDocName) throws IOException {
        String url = "http://localhost:5984/tama-web/" + designDocName + "/_view/all";
        HttpClient httpclient = new DefaultHttpClient();
        System.out.println("Indexing " + designDocName + " " + httpclient.execute(new HttpGet(url)));
    }
}
