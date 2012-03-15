package org.motechproject.tamabatch.couchdb;

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

    static private String host = "localhost";
    static private String port = "5984";

    static private String couchDbBaseUrl() {
        return "http://" + host + ":" + port + "/";
    }

    static String getUrlForView(String databaseName, String designDocName) {
        return String.format("%s%s/%s/_view/all", couchDbBaseUrl(), databaseName, designDocName);
    }

    public static String getUrlForCompaction(String databaseName) {
        return String.format("%s%s/_compact", couchDbBaseUrl(), databaseName);
    }

    public List<String> getDesignDocNames(String dbName) throws IOException, JSONException {
        String url = couchDbBaseUrl() + dbName + "/_all_docs?startkey=%22_design%2F%22&endkey=%22_design0%22&include_docs=true";
        final JSONObject jsonObject = new JSONObject(httpGetAsString(url));
        final JSONArray rows = jsonObject.getJSONArray("rows");
        List<String> result = new LinkedList<String>();
        for (int i = 0; i < rows.length(); i++) {
            result.add(rows.getJSONObject(i).getString("id"));
        }
        return result;
    }

    static private String httpGetAsString(String url) throws JSONException, IOException {
        HttpClient httpclient = new DefaultHttpClient();
        final BasicResponseHandler responseHandler = new BasicResponseHandler();
        return httpclient.execute(new HttpGet(url), responseHandler);
    }


    public static List<String> getApplicationDatabases() throws IOException, JSONException {
        String url = couchDbBaseUrl() + "_all_dbs";
        final JSONArray jsonDBArray = new JSONArray(httpGetAsString(url));
        List<String> result = new LinkedList<String>();
        for (int i=0; i<jsonDBArray.length(); i++) {
            final String dbName = jsonDBArray.getString(i);
            if (!dbName.startsWith("_"))
                result.add(dbName);
        }
        return result;
    }
}