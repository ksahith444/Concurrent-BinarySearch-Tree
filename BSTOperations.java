import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BSTOperations {

	Node parent;
	Node grandparent;
	Node child;
	int counter = 0;

	public Node initialise(Node root) {
		root.rightcild = new Node(root.getKey());
		root.leftchild = new Node(root.getKey() - 1);
		root.leftchild.leftchild = new Node(root.getKey() - 2);
		root.leftchild.rightcild = new Node(root.getKey() - 1);
		return root;
	}

	public boolean insert(int key, Node root) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			Node keynode = searchforwindow(key, root);

			if (keynode.getKey() == key) {
				return false;
			}

			Node node = new Node(key);

			if (child.key < key) {
				if (parent.getRightcild() == child) {
					Node extnode = new Node(key);
					node.rightcild = extnode;
					node.leftchild = child;
					parent.setRightcild(node);
				} else {
					Node extnode = new Node(key);
					node.rightcild = extnode;
					node.leftchild = child;
					parent.setLeftchild(node);
				}
			}

			else {
				Node extnode = new Node(child.getKey());
				child.leftchild = node;
				child.rightcild = extnode;
			}
			return true;
		} finally {
			lock.unlock();
		}

	}

	public boolean delete(int key, Node root) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			Node keynode = searchforwindow(key, root);

			if (keynode.key != key) {
				return false;
			}

			if (parent.getKey() == child.getKey()) {
				if (grandparent.getKey() == child.getKey())
					grandparent.rightcild = parent.leftchild;
				else
					grandparent.leftchild = parent.leftchild;
			} else {
				if (grandparent.getKey() == child.getKey())
					grandparent.rightcild = parent.rightcild;
				else
					grandparent.leftchild = parent.rightcild;
			}

			return true;
		} finally {
			lock.unlock();
		}
	}

	public Node searchforwindow(int key, Node root) {
		counter = 0;
		Node node = findparents(key, root);
		return node;
	}

	public Node findparents(int key, Node root) {

		if (counter == 0)
			child = root;

		if (counter == 1) {
			parent = child;
			child = root;
		}

		if (counter >= 2) {
			grandparent = parent;
			parent = child;
			child = root;
		}
		counter++;
		Node node = searchforkey(key, root);
		return node;

	}

	public Node searchforkey(int key, Node root) {

		if (root.getKey() != key && root.getLeftchild() == null && root.getRightcild() == null) {
			// System.out.println("key not found");
			return root;
		}
		if (root.getKey() == key && root.getLeftchild() == null && root.getRightcild() == null) {
			System.out.println("key found");
			return root;
		}
		if (root.getKey() <= key)
			return findparents(key, root.getRightcild());

		return findparents(key, root.getLeftchild());

	}

	public boolean search(int key, Node root) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			if (root == null)
				return false;

			if (root.getKey() == key && root.getLeftchild() == null && root.getRightcild() == null) {
				return true;
			}
			if (root.getKey() <= key)
				return search(key, root.getRightcild());

			return search(key, root.getLeftchild());
		} finally {
			lock.unlock();
		}
	}

	public boolean isValidBST(Node root) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		} finally {
			lock.unlock();
		}
	}

	public boolean isValidBST(Node p, double min, double max) {
		if (p == null)
			return true;

		if (p.key < min || p.key >= max)
			return false;

		return isValidBST(p.leftchild, min, p.key) && isValidBST(p.rightcild, p.key, max);
	}

}
