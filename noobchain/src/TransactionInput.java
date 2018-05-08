package src;

public class TransactionInput {
	public String transactionOutputId;
	public TransactionOutput UTXO; //unspent transaction output;

	public TransactionInput (String transationOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}

//this class contains all of the input targeted to you, that
//means how much coins you have to transact
