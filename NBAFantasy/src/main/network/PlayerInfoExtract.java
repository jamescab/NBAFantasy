package network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class PlayerInfoExtract {
    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJfromURL("http://data.nba.net/10s/prod/v1/2019/players.json");
        JSONArray jsonArray = json.getJSONObject("league").getJSONArray("standard");
        PrintWriter writer = new PrintWriter("./data/roster.txt", "UTF-8");

        for (int i = 0; i < jsonArray.length(); i++) {
            //System.out.println(jsonArray.get(i));
            String firstName = (String) jsonArray.getJSONObject(i).get("firstName");
            String lastName = (String) jsonArray.getJSONObject(i).get("lastName");
            String position = (String) jsonArray.getJSONObject(i).get("pos");
            System.out.println(firstName + " " + lastName);
            String line = firstName + " " + lastName + "/" + position + "/" + 0;
            //writer.println(line);
        }
        //writer.close();
    }

    //Helper methods obtained from https://stackoverflow.com/a/4308662
    public static String readAll(Reader r) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = r.read()) != -1) {
            sb.append((char) c);
        }
        return sb.toString();
    }

    public static JSONObject readJfromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
