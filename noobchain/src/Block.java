


package src;

import java.util.Date;

public class Block {
	
	/* @Author David
	 * this is a copy of the implementation from the website
	 * https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa
	 */
	
	public String hash;
	public String previoushash;
	public String merkleRoot;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private String data;
	private long timeStamp; //time of creation of the block
	private int nonce;
	
	//Constructor
	public Block(/*String data,*/String previoushash) {
		//this.data = data;
		this.previoushash = previoushash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previoushash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				//data
				merkleRoot
				);
		return calculatedhash;
	}
	
	public void mineBlock(int dificulty) {
		String target = new String(new char[dificulty]).replace('\0', '0'); //)
		while(!hash.substring(0,dificulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!!: " + hash);
	}
	
	//Add transactions to this block
		public boolean addTransaction(Transaction transaction) {
			//process transaction and check if valid, unless block is genesis block then ignore.
			if(transaction == null) return false;		
			if((previousHash != "0")) {
				if((transaction.processTransaction() != true)) {
					System.out.println("Transaction failed to process. Discarded.");
					return false;
				}
			}
			transactions.add(transaction);
			System.out.println("Transaction Successfully added to Block");
			return true;
		}
}
