package noobchain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
		public PublicKey publicKey;
		public PrivateKey privateKey;
		
		public HashMap<String,TransactionOutput> UTXOs = new HashMap<String.TransactionOutput>();
		
		public Wallet() {
			generateKeyPair();
		}
		
		public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			keyGen.initialize(ecSpec, random);
			KeyPair keyPair = keyGen.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public float getBalance() {
		float total = 0;
		for(Map.Entry<String, TransactionOutput> item: NoobChain.UTXO.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			if(UTXO.isMine(publicKey)) {// does this belong to me?
				UTXOs.put(UTXO.ID, UTXO);
				total += UTXO.value;
			}
		}
		return total;
	}
	//generate and returns a new transaction from this wallet.
	public Transaction sendFunds (PublicKey _recipient,float value) {
		if(getBalance() < value) {
			System.out.println("#not enough funs to send");
			return null;
		}
		//generating an array list of inputs
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	    
		float total = 0;
		for (Map.Entry<String, TransactionOutput> item: UTXOs.entrySet()){
			TransactionOutput UTXO = item.getValue();
			total += UTXO.value;
			inputs.add(new TransactionInput(UTXO.ID));
			if(total > value) break;
		}
		
		Transaction newTransaction = new Transaction(publicKey, _recipient , value, inputs);
		newTransaction.generateSignature(privateKey);
		
		for(TransactionInput input: inputs){
			UTXOs.remove(input.transactionOutputId);
		}
		return newTransaction;
	}
}
