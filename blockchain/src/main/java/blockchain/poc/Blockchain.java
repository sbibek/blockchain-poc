package blockchain.poc;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

public class Blockchain {
	private List<Block> blockchain = new ArrayList<Block>();
	private int complexity = 5;
	private String miningHash;
	private String hashTarget;

	public Blockchain(int complexity) {
		this.complexity = complexity;
		this.hashTarget = new String(new char[complexity]).replace("\0", "0");
	}

	public void addBlock(String message) {
		if (blockchain.size() > 0) {
			Block previous = blockchain.get(blockchain.size() - 1);
			blockchain.add(new Block(message, previous.hash));
		} else {
			// this is genesis block
			blockchain.add(new Block(message, "0"));
		}
	}

	public Block get(int i) {
		return blockchain.get(i);
	}

	public Boolean isValid() {
		Block current, previous;
		for (int i = 1; i < blockchain.size(); i++) {
			current = blockchain.get(i);
			previous = blockchain.get(i - 1);
			if (!current.hash.equals(current.getHash())) {
				System.out.println("FATAL: current hash doesnt match with calculated");
				return false;
			}
			if (!previous.hash.equals(current.previousHash)) {
				System.out.println("FATAL: prev hash doesnt match");
				return false;
			}
			if (!current.hash.substring(0, complexity).equals(hashTarget)) {
				System.out.println("FATAL: unmined block");
				return false;
			}
		}
		return true;
	}

	public void log() {
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);

	}
}