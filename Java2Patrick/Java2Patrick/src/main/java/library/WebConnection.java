package library;

/**
 * Created by Patrick on 9/2/13.
 */
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class WebConnection {

    static Boolean connection = false;
    static String connectionType = "Unavailable";

    public static String getConnectionType(Context context) {
        netInfo(context);
        return connectionType;
    }

    public static Boolean getConnectionStatus(Context context) {
        netInfo(context);
        return connection;
    }

    private static void netInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null) {
            if(ni.isConnected()) {
                connectionType = ni.getTypeName();
                connection = true;
            }
        }
    }

    public static String getURLStringResponse(URL url) {
        String response = "";

        try{
            URLConnection conn = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());

            byte[] contentBytes = new byte[1024];
            int bytesRead = 0;
            StringBuffer responseBuffer = new StringBuffer();

            while((bytesRead = bin.read(contentBytes)) != -1) {
                response = new String(contentBytes, 0, bytesRead);
                responseBuffer.append(response);
            }

            return responseBuffer.toString();
        } catch (Exception e) {
            Log.e("URL RESPONSE ERROR", e.getLocalizedMessage());
        }

        return response;
    }

}