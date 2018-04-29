package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.Security;
import java.util.Base64;


public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int dificulty = 2;
	public static Wallet walletA;
	public static Wallet walletB;
	//adding 2 wallets after completing the StringUtil and Transaction

	public static void main(String[] args) {
		
		
		//this is the second part added after compleating the transaction code
		//please check bouncycastle API. It is not yet integrated
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		walletA = new Wallet ();
		walletB = new Wallet();
		
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		Transaction transaction = new Transaction (walletA.publicKey,walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		System.out.println("Is signature verified?");
		System.out.println(transaction.verifySignature());
		
		
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
		}
		return true;
	}
	
}
