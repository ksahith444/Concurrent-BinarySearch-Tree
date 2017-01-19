
public class Node {

	int key;
	Node leftchild;
	Node rightcild;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getLeftchild() {
		return leftchild;
	}

	public void setLeftchild(Node leftchild) {
		this.leftchild = leftchild;
	}

	public Node getRightcild() {
		return rightcild;
	}

	public void setRightcild(Node rightcild) {
		this.rightcild = rightcild;
	}

	public Node(int key) {
		this.key = key;
		leftchild = null;
		rightcild = null;
	}

}
