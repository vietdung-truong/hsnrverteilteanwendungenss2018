


package noobchain;

import java.util.Date;

public class Block {
	
	public String hash;
	public String previoushash;
	private String data;
	private long timeStamp;
	
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
				data
				);
		return calculatedhash;
	}
}
