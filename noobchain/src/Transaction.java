package src;
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
	
	public boolean processTransaction() {
		if (verifySignature() == false) {
			System.out.println("transaction signature is false");
			return false;
		}
		
		//gathering inputs
		for (TransactionInput i : inputs) {
			i.UTXO = NoobChain.UTXO.get(i.transactionOutputID);
		}
		
		//is the transaction valid?
		if(getInputsValue() < NoobChain.minimunTransaction) {
			System.out.println("#Transaction Input too small:" + getInputsValue());
			return false;
		}
		
		//generate transaction outputs
		float LeftOver = getInputsValue() - value;
		transactionID = calculateHashj();
		outputs.add(new TransactionOutput(this.recipient, value, transactionID)); //send value to recipient
		outputs.add(new TransactionOutput(this.sender, LeftOver, transactionID)); //get "changes" back
		
		//add to unspent list
		for (TransactionOutput o : outputs) {
			NoobChain.UTXOd.out(o.id, o);
		}
		
		for(TransactionInput i = inputs) {
			if(i.UTXO == null) continue;
			NoobChain.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
	}
	
	//get the number of unspent input
	public float getInputsValue() {
		float total = 0;
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue;
			total += i.UTXO.value;
		}
		return total;
	}
	
	public float getOutputsValue() {
		float total = 0;
		for(TransactionOutput o : outputs)
		{
			total += o.value;
		}
		return total;
	}
}
