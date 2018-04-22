


package noobchain;

import java.util.Date;

public class Block {
	
	/* @Author David
	 * this is a copy of the implementation from the website
	 * https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa
	 */
	
	public String hash;
	public String previoushash;
	private String data;
	private long timeStamp; //time of creation of the block
	private int nonce;
	
	//Constructor
	public Block(String data,String previoushash) {
		this.data = data;
		this.previoushash = previoushash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previoushash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data
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
}
