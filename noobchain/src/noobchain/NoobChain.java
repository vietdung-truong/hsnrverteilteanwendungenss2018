package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static int dificulty = 2;

	public static void main(String[] args) {
		
		
		blockchain.add(new Block ("Hi im the first block", "0"));
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
