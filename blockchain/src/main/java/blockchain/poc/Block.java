package blockchain.poc;

import java.util.Date;

public class Block {
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = this.getHash();
	}

	public String getHash() {
		return Util.sha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
	}

	public void mine(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = getHash();
		}
		System.out.println("SUCCESS: Block Mined!!! : " + hash);
	}
}
