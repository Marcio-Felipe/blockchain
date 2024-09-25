package commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import managedbeans.JsonRpcResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class LibraryCommons {

    public static JsonRpcResponse rpcParser(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, JsonRpcResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
