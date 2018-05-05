package blockchain.poc;

public class Main {
	public static void main(String[] args) {
		int complexity = 5;
		Blockchain blockchain = new Blockchain(complexity);
		blockchain.addBlock("genesis");
		blockchain.get(0).mine(complexity);
		blockchain.addBlock("second");
//		blockchain.get(1).mine(complexity);
		System.out.println(blockchain.isValid());
	}
}
