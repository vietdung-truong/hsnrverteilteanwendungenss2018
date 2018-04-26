package noobchain;
import java.security.*;
import java.util.ArrayList;

public class Transaction {
	public String transactionID;
	public PublicKey sender;
	public PublicKey recipient;
	public Float value;
	public byte[] signature; //prevent 3rd party from spending our fund.
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs =  new ArrayList<TransactionOutput>();
}
