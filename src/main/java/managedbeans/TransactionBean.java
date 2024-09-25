package managedbeans;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;

@ManagedBean(name = "transactionBean")
@SessionScoped
public class TransactionBean {
    
    private String fromAddress;
    private String toAddress;
    private BigDecimal amount;
    private String message;

    // Getters e Setters
    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendTransaction() {
        try {
            Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545")); // Conecte-se ao nó Ethereum

            // Substitua pela chave privada correspondente ao endereço from
            String privateKey = "0x519765efd78a18d4fad73ad8ff2a4a8c220064aec01f7cd42e6217a726f467c5";
            Credentials credentials = Credentials.create(privateKey);

            // Enviar ETH
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3, credentials, toAddress, amount, Convert.Unit.ETHER).send();

            // Atualizar mensagem de sucesso
            message = "Transação completa! Hash: " + transactionReceipt.getTransactionHash();
        } catch (Exception e) {
            message = "Erro ao enviar a transação: " + e.getMessage();
            e.printStackTrace();
        }
    }

    public void sendTransaction(String fromAddress, String toAddress, BigDecimal amount) {
        try {
            Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545"));
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3, Credentials.create(""), toAddress, amount, Convert.Unit.ETHER).send();

            // Atualizar mensagem de sucesso
            message = "Transação completa! Hash: " + transactionReceipt.getTransactionHash();
        } catch (Exception e) {
            message = "Erro ao enviar a transação: " + e.getMessage();
            e.printStackTrace();
        }
    }
}
