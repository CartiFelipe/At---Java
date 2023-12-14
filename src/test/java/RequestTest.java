import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestTest {
    @Test
    public void test() throws IOException {
        URL apiUrl = new URL("http://localhost:4567/usuarios");

        // Estabelecer conexão
        HttpURLConnection conection = (HttpURLConnection) apiUrl.openConnection();
        conection.setRequestMethod("GET");


        int responseCode = conection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);

    }
}
