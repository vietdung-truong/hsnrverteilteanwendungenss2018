package noobchain;

import java.security.*;

public class TransactionOutput {
	public String ID;
	public PublicKey recipient;
	public float value;
	public String parentTransactionID;
	
	public TransactionOutput(PublicKey recipient, float value, String parentTransactionID) {
		this.recipient = recipient;
		this.value = value;
		this.parentTransactionID = parentTransactionID;
		this.ID = StringUtil.applySha256(StringUtil.getStringFromKey(recipient)
				+ Float.toString(value)
				+ parentTransactionID
				);
	}
	
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == recipient);
	}
}
