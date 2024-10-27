package br.com.gbvbahia.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
//https://www.youtube.com/watch?v=H5JubkIy_p8&t=237s
/* Max number of nodes at level = 2 ˆ level, l1 = 2^1=2, l2 = 2ˆ2=4 l3 = 2ˆ3=8 ... */
//https://www.youtube.com/watch?v=gcULXE7ViZw&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=42
@Data
@NoArgsConstructor
public class BinaryTree<T extends BinaryTreeValue<T>> {

	private final Logger log = Logger.getLogger(getClass().getName());

	private BinaryTree<T> left;
	private BinaryTree<T> right;
	private T value;

	public BinaryTree(T value) {
		super();
		this.value = value;
	}

	public boolean hasValue() {
		return value != null;
	}

	public boolean hasLeftValue() {
		return left != null && left.value != null;
	}

	public boolean hasRightValue() {
		return right != null && right.value != null;
	}

	public BinaryTree<T> insert(T val) {
		if (value == null) {
			this.value = val;
			return this;
		}

		if (val.compareTo(this.value) < 0) {
			if (left == null) left = new BinaryTree<>(val);
			else left = left.insert(val);
		} else {
			if (right == null) right = new BinaryTree<>(val);
			else right = right.insert(val);
		}

		return balance();
	}


	public int calculateHeight() {
		if (value == null) {
			return 0;
		}
		int leftHeight = (left != null) ? left.calculateHeight() : 0;
		int rightHeight = (right != null) ? right.calculateHeight() : 0;
		return Math.max(leftHeight, rightHeight) + 1;
	}


	private BinaryTree<T> balance() {
		int balanceFactor = getBalanceFactor();

		if (balanceFactor > 1) {
			if (left != null && left.getBalanceFactor() >= 0) {
				return rotateRight();
			} else if (left != null) {
				left = left.rotateLeft();
				return rotateRight();
			}
		} else if (balanceFactor < -1) {
			if (right != null && right.getBalanceFactor() <= 0) {
				return rotateLeft();
			} else if (right != null) {
				right = right.rotateRight();
				return rotateLeft();
			}
		}
		return this;
	}

	private int getBalanceFactor() {
		int leftHeight = (left != null) ? left.calculateHeight() : 0;
		int rightHeight = (right != null) ? right.calculateHeight() : 0;
		return leftHeight - rightHeight;
	}

	private BinaryTree<T> rotateRight() {
		BinaryTree<T> newRoot = left;
		left = newRoot.right;
		newRoot.right = this;
		return newRoot;
	}

	private BinaryTree<T> rotateLeft() {
		BinaryTree<T> newRoot = right;
		right = newRoot.left;
		newRoot.left = this;
		return newRoot;
	}


	public Optional<T> search(T toSearch) {
		if (!hasValue()) {
			return Optional.empty();
		}

		int compare = this.value.compareTo(toSearch);

		if (compare == 0) {
			return Optional.of(value);
		}

		if (compare > 0) {
			return getLeft().search(toSearch);
		}
		return getRight().search(toSearch);
	}

	public BinaryTree<T> getRight() {
		if (right == null) {
			right = new BinaryTree<>();
		}
		return right;
	}

	public BinaryTree<T> getLeft() {
		if (left == null) {
			left = new BinaryTree<>();
		}
		return left;
	}

	@Override
	public String toString() {
		List<List<String>> levels = getNodesByLevel();
		int maxLevel = levels.size();
		int maxWidth = (int) Math.pow(2, maxLevel) * 6;

		StringBuilder builder = new StringBuilder();
        for (List<String> level : levels) {
            int levelNodes = level.size();
            int spacesBetween = maxWidth / levelNodes;
            StringBuilder line = new StringBuilder();

            for (String nodeValue : level) {
                line.append(getSpace(spacesBetween / 2 - nodeValue.length() / 2));
                line.append(nodeValue);
                line.append(getSpace(spacesBetween / 2 - nodeValue.length() / 2));
            }
            builder.append(line.toString());
            builder.append("\n");
        }
		return builder.toString();
	}

	private List<List<String>> getNodesByLevel() {
		List<List<String>> levels = new ArrayList<>();
		Queue<BinaryTree<T>> queue = new LinkedList<>();
		queue.add(this);

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<String> levelNodes = new ArrayList<>();

			for (int i = 0; i < levelSize; i++) {
				BinaryTree<T> node = queue.poll();
				if (node != null && node.hasValue()) {
					levelNodes.add(node.value.toString());
					queue.add(node.left != null ? node.left : new BinaryTree<>());
					queue.add(node.right != null ? node.right : new BinaryTree<>());
				} else {
					levelNodes.add(" ");
					queue.add(new BinaryTree<>());
					queue.add(new BinaryTree<>());
				}
			}
			levels.add(levelNodes);

			// Check if all next nodes are empty to avoid infinite loops
			boolean allNull = queue.stream().allMatch(n -> n == null || !n.hasValue());
			if (allNull) {
				break;
			}
		}
		return levels;
	}

	private String getSpace(int count) {
		return " ".repeat(Math.max(0, count));
	}

	/**
	 * left root right
	 * Depth first
	 * @return
	 */
	public String inorder() {
	  StringBuilder builder = new StringBuilder("\n");
		return inorderRecursive(this, builder);
	}

	private String inorderRecursive(BinaryTree<T> root, StringBuilder builder) {
		if (root != null && root.hasValue()) {
		  inorderRecursive(root.left, builder);
		  fillStringBuilder(root, builder);
		  inorderRecursive(root.right, builder);
		}
		return builder.toString();
	}

	/**
	 * root left right
	 * Depth first
	 * @return
	 */
	public String preorder() {
	  StringBuilder builder = new StringBuilder("\n");
	  return preorderRecursive(this, builder);
	}

    private String preorderRecursive(BinaryTree<T> root, StringBuilder builder) {
        if (root != null && root.hasValue()) {
          fillStringBuilder(root, builder);
          preorderRecursive(root.left, builder);
          preorderRecursive(root.right, builder);
        }
        return builder.toString();
    }

    /**
     * left right root
     * Depth first
     * @return
     */
    public String postorder() {
      StringBuilder builder = new StringBuilder("\n");
      return postorderRecursive(this, builder);
    }

    private String postorderRecursive(BinaryTree<T> root, StringBuilder builder) {
        if (root != null && root.hasValue()) {
          postorderRecursive(root.left, builder);
          postorderRecursive(root.right, builder);
          fillStringBuilder(root, builder);
        }
        return builder.toString();
    }

    private void fillStringBuilder(BinaryTree<T> root, StringBuilder builder) {
      builder.append("L: ").append(root.getLeft().value).append(" ")
           .append("M: ").append(root.value).append(" ")
           .append("R: ").append(root.getRight().value)
           .append("\n");
    }

    public String breadthFirst() {
      LinkedList<T> queue = new LinkedList<>();
      queue.add(this.value);
      breadthFirstQueue(this, queue);
      return breadthFirstString(queue);
    }

    private void breadthFirstQueue(BinaryTree<T> root, Queue<T> queue) {
      if (root != null && root.hasValue()) {
        queue.add(root.getLeft().value);
        queue.add(root.getRight().value);
        breadthFirstQueue(root.getLeft(), queue);
        breadthFirstQueue(root.getRight(), queue);
      }
    }

    private String breadthFirstString(Queue<T> queue) {
      StringBuilder builder = new StringBuilder("\n");
      while (!queue.isEmpty()) {
        T poll = queue.poll();
        if (poll != null) {
          builder.append(poll);
        }
      }
      return builder.toString();
    }
}
