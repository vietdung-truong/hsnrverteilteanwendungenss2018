package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;


public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap <String,TransactionOutput>();
	
	public static int dificulty = 4; //Commit Test Mo
	public static float minimumTransaction = 0.1f;
	public static Wallet walletA;
	public static Wallet walletB;
	//adding 2 wallets after completing the StringUtil and Transaction
	public static Transaction genesisTransaction;
	//added after completing transaction. This is the ammount of money released

	public static void main(String[] args) {
		
		
		//this is the second part added after completing the transaction code
		//please check bouncycastle API. It is not yet integrated
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		walletA = new Wallet ();
		walletB = new Wallet();
		Wallet Coinbase = new Wallet();
		
		
		//copied due to lack of time
		genesisTransaction = new Transaction(Coinbase.publicKey, walletA.publicKey, 100f, null);
		genesisTransaction.generateSignature(Coinbase.privateKey);	 //manually sign the genesis transaction	
		genesisTransaction.transactionID = "0"; //manually set the transaction id
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.recipient, genesisTransaction.value, genesisTransaction.transactionID)); //manually add the Transactions Output
		UTXOs.put(genesisTransaction.outputs.get(0).ID, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
		
		Block genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
		
		
		//testing transaction
		Block block1 = new Block(genesis.hash);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("\nWalletA is Attempting to send funds (40) to WalletB...");
		block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
		addBlock(block1);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		
		Block block2 = new Block(block1.hash);
		System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
		block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
		addBlock(block2);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		
		Block block3 = new Block(block2.hash);
		System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
		block3.addTransaction(walletB.sendFunds( walletA.publicKey, 20));
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		
		isChainValid();
		
		
		/*System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		Transaction transaction = new Transaction (walletA.publicKey,walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		System.out.println("Is signature verified?");
		System.out.println(transaction.verifySignature());
		*/
		
		//this is the first part
		/*blockchain.add(new Block ("Hi im the first block", "0"));
		System.out.println("Trying to mine...");
		blockchain.get(0).mineBlock(dificulty);
		blockchain.add(new Block ("Yo im the second block and I have extra text for you",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine...");
		blockchain.get(1).mineBlock(dificulty);
		blockchain.add(new Block ("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine...");
		blockchain.get(2).mineBlock(dificulty);
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		
		System.out.println('\0');
		System.out.println(blockchainJson);
		*/
	
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[dificulty]).replace('\0', '0');
		HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
		tempUTXOs.put(genesisTransaction.outputs.get(0).ID, genesisTransaction.outputs.get(0));
		
		//loop through blockchain to check hashes:
			for (int i = 1; i < blockchain.size(); i++) {
				currentBlock = blockchain.get(i);
				previousBlock = blockchain.get(i-1);
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes are not equal!");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previoushash)) {
				System.out.println("previous Hashes not equal");
				return false;
			}
			if(!currentBlock.hash.substring(0,dificulty).equals(hashTarget)) {
				System.out.println("This hasn't been mined yet");
				return false;
			}
			//loop thru blockchains transactions:
			TransactionOutput tempOutput;
			for(int t=0; t <currentBlock.transactions.size(); t++) {
				Transaction currentTransaction = currentBlock.transactions.get(t);
				
				if(!currentTransaction.verifySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}
				if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
					return false; 
				}
				
				for(TransactionInput input: currentTransaction.inputs) {	
					tempOutput = tempUTXOs.get(input.transactionOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.transactionOutputId);
				}
				
				for(TransactionOutput output: currentTransaction.outputs) {
					tempUTXOs.put(output.ID, output);
				}
				
				if( currentTransaction.outputs.get(0).recipient != currentTransaction.recipient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				if( currentTransaction.outputs.get(1).recipient != currentTransaction.sender) {
					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
					return false;
				}
				
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(dificulty);
		blockchain.add(newBlock);
	}
	
}
