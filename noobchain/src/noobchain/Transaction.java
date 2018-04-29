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
	
	private static int sequence = 0;
	
	//Konstruktor
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.recipient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	private String calculateHash() {
		sequence ++;
		return StringUtil.applySha256(
				StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(recipient) +
				Float.toString(value) + sequence);
	}
	
	
	//this was put after the expansion of StringUtil to ECDSA
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient);
		signature = StringUtil.applyECDSASig(privateKey, data);
	}
	
	public boolean verifySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
	
}
