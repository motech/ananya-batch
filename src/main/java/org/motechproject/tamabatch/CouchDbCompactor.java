package org.motechproject.tamabatch;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CouchDbCompactor {

    public void compactAllDatabases() throws IOException, JSONException {
        for (String applicationDatabase : CouchDbMetaData.getApplicationDatabases()) {
            final String urlForCompaction = CouchDbMetaData.getUrlForCompaction(applicationDatabase);
            compact(urlForCompaction);
        }
    }

    private boolean compact(String urlForCompaction) throws IOException, JSONException {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(urlForCompaction);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
        final String response = httpClient.execute(httpPost, new BasicResponseHandler());
        final JSONObject jsonResult = new JSONObject(response);
        return jsonResult.getBoolean("ok");
    }
}
