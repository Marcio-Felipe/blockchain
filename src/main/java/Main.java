import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

public class Main {
    public static void main(String[] args) {
        // Conectar ao nó Ethereum
        Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545")); // Altere para o seu nó

        try {
            // Obter o número do bloco mais recente
            EthBlock block = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
            System.out.println("Latest block number: " + block.getBlock().getNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}