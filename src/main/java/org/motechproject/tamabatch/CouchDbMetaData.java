package org.motechproject.tamabatch;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CouchDbMetaData {

    private String dbName;

    public CouchDbMetaData(String dbName) {
        this.dbName = dbName;
    }

    List<String> getDesignDocNames() throws IOException, JSONException {
        String url = "http://localhost:5984/" + dbName + "/_all_docs?startkey=%22_design%2F%22&endkey=%22_design0%22&include_docs=true";
        HttpClient httpclient = new DefaultHttpClient();
        final BasicResponseHandler responseHandler = new BasicResponseHandler();
        final JSONObject jsonObject = new JSONObject(httpclient.execute(new HttpGet(url), responseHandler));
        final JSONArray rows = jsonObject.getJSONArray("rows");
        List<String> result = new LinkedList<String>();
        for (int i = 0; i < rows.length(); i++) {
            result.add(rows.getJSONObject(i).getString("id"));
        }
        return result;
    }
}