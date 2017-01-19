import java.util.Random;

public class RelativeDistribution implements Runnable {

	volatile int caseNumber;
	volatile int keyspace;
	volatile BSTOperations bstops;
	volatile Node root;
	volatile Random random;
	int counter = 1000000;

	public RelativeDistribution(int caseNumber, int keyspace, BSTOperations bstops, Node root, Random random) {
		this.caseNumber = caseNumber;
		this.keyspace = keyspace;
		this.bstops = bstops;
		this.root = root;
		this.random = random;
	}

	public void run() {
		while (counter > 0) {
			int key = random.nextInt(keyspace + 1);
			double prob = key / keyspace;
			boolean isSuccessfull = false;
			boolean isValid = false;
			switch (caseNumber) {
			case 1:
				if (prob <= 0.9){
					isSuccessfull = bstops.search(key, root);
					System.out.print("search");
				}
				else if (0.9 < prob && prob <= 0.99){
					isSuccessfull = bstops.insert(key, root);
					System.out.print("insert");
				}
				else{
					isSuccessfull = bstops.delete(key, root);
					System.out.print("delete");
				}
				break;

			case 2:
				if (prob <= 0.7){
					isSuccessfull = bstops.search(key, root);
					System.out.print("search");
				}
				else if (0.7 < prob && prob <= 0.9){
					isSuccessfull = bstops.insert(key, root);
					System.out.print("insert");
				}
				else{
					isSuccessfull = bstops.delete(key, root);
					System.out.print("delete");
				}
				break;

			case 3:
				if (prob <= 0.5){
					isSuccessfull = bstops.insert(key, root);
					System.out.print("insert");
				}
				else{
					isSuccessfull = bstops.delete(key, root);
					System.out.print("delete");
				}
				break;
			}

			if (isSuccessfull)
				System.out.println(" opeartion is successfull");
			else
				System.out.println(" opeartion is not successfull");

			isValid = bstops.isValidBST(root);

			if (isValid)
				System.out.println("the tree is valid");
			else
				System.out.println("the tree is not valid");

			counter--;
		}
	}
}
