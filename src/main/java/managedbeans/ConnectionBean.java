package managedbeans;


import commons.LibraryCommons;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.net.URI;

@ManagedBean(name = "connectionBean")
@SessionScoped
public class ConnectionBean {
    private String contractAddress;
    private String message;
    private JsonRpcResponse response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String connectToService(String uri, String jsonRPCRequest) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URI.create(uri));
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            HttpEntity httpEntity = new StringEntity(jsonRPCRequest, "UTF-8");
            request.setEntity(httpEntity);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                message = "Conectado com sucesso ao microserviço!";
            } else {
                message = "Falha ao conectar ao microserviço. Status: " + response.getStatusLine().getStatusCode();
            }

            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);
            return responseBody;// Opcional: imprimir corpo da resposta
        } catch (Exception e) {
            message = "Erro: " + e.getMessage();
        }
        return null;
    }

    public void getBlockNumber() {
        String jsonRpcRequest = "{\n" +
                "\t\"jsonrpc\":\"2.0\",\n" +
                "\t\"method\":\"eth_blockNumber\",\n" +
                "\t\"params\":[],\n" +
                "\t\"id\":1337\n" +
                "}";
        String response = connectToService("http://localhost:8055/localrpc/block-number", jsonRpcRequest);
        JsonRpcResponse jsonRpcResponse = LibraryCommons.rpcParser(response);
        this.response = jsonRpcResponse;

    }

    public void getContractByteCode(String contractAddress) {

        String jsonRpcRequest = "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"eth_getCode\",\n" +
                "  \"params\": [\n" +
                "    \"" + contractAddress + "\",\n" +
                "    \"latest\"\n" +
                "  ],\n" +
                "  \"id\": 1\n" +
                "}";

        String response = connectToService("http://localhost:8055/localrpc/block-number", jsonRpcRequest);
        JsonRpcResponse jsonRpcResponse = LibraryCommons.rpcParser(response);

        this.response = jsonRpcResponse;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public JsonRpcResponse getResponse() {
        return response;
    }

    public void setResponse(JsonRpcResponse response) {
        this.response = response;
    }
}
