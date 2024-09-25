import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Convert;

import java.math.BigDecimal;


public class SendEthTransaction {
    public static void main(String[] args) {
        // Conectar ao nó Ethereum (neste caso, utilizando Ganache)
        Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545"));

        try {
            // Chave privada da conta de origem (substitua pela sua chave privada)
            String privateKey = "0x1c934ce9c992e1ade62580600c8053d9f220698b053b62d1e000a9c93b1b7da5";
            Credentials credentials = Credentials.create(privateKey);

            // Conta de destino (substitua pelo endereço do destinatário)
            String recipientAddress = "0x77e7e3410CB5ee84a2c372a850e261565b594a6c";

            // Valor a ser enviado em Ether
            BigDecimal value = BigDecimal.valueOf(99); // 0.01 ETH

            // Cria uma instância de TransactionManager para gerenciar a transação
            RawTransactionManager transactionManager = new RawTransactionManager(web3, credentials);

            // Enviar a transação
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3, credentials, recipientAddress, value, Convert.Unit.ETHER).send();

            // Exibir detalhes da transação
            System.out.println("Transação completa. Hash da transação: " + transactionReceipt.getTransactionHash());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}