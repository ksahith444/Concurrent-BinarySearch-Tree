import java.util.Random;

public class BSTmain {

	static double delay = 0;

	public static void main(String[] args) {

		int numberofthreads = 0;
//		double mean = 0;

		for (int q = 2; q <= 32; q = q * 2) {
			numberofthreads = q;

//			for (int p = 1; p <= 10; p++) {
//				mean = p;

				Thread[] t = new Thread[numberofthreads];

				for (int i = 1000; i <= 1000000; i = i * 10) {
					int keyspace = i;
					Node root1 = new Node(keyspace);
					BSTOperations bstops = new BSTOperations();
					Node root = bstops.initialise(root1);

					for (int j = 1; j <= 3; j++) {
						int caseNumber =j;
						Random r = new Random();
						
						long start = System.currentTimeMillis();
						
//						for (int k = 0; k < 1000000; k++) {
							for (int a = 0; a < t.length; a++) {
								t[a] = new Thread(new RelativeDistribution(caseNumber, keyspace, bstops, root, r));
								t[a].start();
//								delay = BSTmain.exp_rv(r, mean);
//								try {
//									Thread.sleep((long) (delay * 0.001));
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
							}
//						}
//						for (int k = 0; k < 1000000; k++) {
							for (int b = 0; b < t.length; b++) {
								try {
									t[b].join();
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
								}
							}

//						}
						//System.out.println("ending....");
						long end = System.currentTimeMillis();
						System.out.println("Time taken for " + numberofthreads + " to update tree with size " 
												+keyspace + " and relative distribution for case " + caseNumber 
												+ " is " + (end - start));
					}

				}

			}
		}
		// Node root1 = new Node(10);
		// BSTOperations bstops = new BSTOperations();
		// Node root = bstops.initialise(root1);
		//
		// bstops.insert(2, root);
		// boolean istrue = bstops.isValidBST(root);
		// System.out.println(istrue);
		//
		// bstops.insert(5, root);
		//
		// istrue = bstops.isValidBST(root);
		// System.out.println("valid after inserting 5 "+istrue);
		//
		// istrue = bstops.insert(2, root);
		// System.out.println("insert 2 " +istrue);
		//
		// istrue = bstops.isValidBST(root);
		// System.out.println("valid after inserting 2 "+ istrue);
		//
		// istrue = bstops.insert(7, root);
		// System.out.println("insert 7 " +istrue);
		//
		// istrue = bstops.isValidBST(root);
		// System.out.println("valid after inserting 7 " + istrue);
		//
		// istrue = bstops.delete(5, root);
		// System.out.println("deleting 5 " +istrue);
		//
		// istrue = bstops.isValidBST(root);
		// System.out.println("valid after deleting 5 " + istrue);
		//
		// istrue = bstops.search(7, root);
		// System.out.println("searching 7 " +istrue);
//	}

//	public static double exp_rv(Random r, double mean) {
//		return -(Math.log(1 - r.nextDouble()) * mean);
//	}

}
