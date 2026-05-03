import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Pair> {
    private TreeNode root;
    private int treeSize;

    private class TreeNode {
        K key;
        V val;
        TreeNode left, right;

        public TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public class Pair {
        private K key;
        private V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    public int size() {
        return treeSize;
    }


    public void put(K key, V val) {
        if (root == null) {
            root = new TreeNode(key, val);
            treeSize++;
            return;
        }

        TreeNode temp = root;
        while (true) {
            int comparison = key.compareTo(temp.key);
            if (comparison < 0) {
                if (temp.left == null) {
                    temp.left = new TreeNode(key, val);
                    treeSize++;
                    return;
                }
                temp = temp.left;
            } else if (comparison > 0) {
                if (temp.right == null) {
                    temp.right = new TreeNode(key, val);
                    treeSize++;
                    return;
                }
                temp = temp.right;
            } else {
                temp.val = val;
                return;
            }
        }
    }


    public V get(K key) {
        TreeNode temp = root;
        while (temp != null) {
            int comparison = key.compareTo(temp.key);
            if (comparison < 0) {
                temp = temp.left;
            } else if (comparison > 0) {
                temp = temp.right;
            } else {
                return temp.val;
            }
        }
        return null;
    }


    public void delete(K key) {
        TreeNode parent = null;
        TreeNode target = root;

        while (target != null && !target.key.equals(key)) {
            parent = target;
            if (key.compareTo(target.key) < 0) {
                target = target.left;
            } else {
                target = target.right;
            }
        }

        if (target == null) return;


        if (target.left == null || target.right == null) {
            TreeNode childNode = (target.left != null) ? target.left : target.right;

            if (parent == null) {
                root = childNode;
            } else if (parent.left == target) {
                parent.left = childNode;
            } else {
                parent.right = childNode;
            }
            treeSize--;
        }

        else {
            TreeNode minNodeParent = target;
            TreeNode minNode = target.right;

            while (minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }

            target.key = minNode.key;
            target.val = minNode.val;

            if (minNodeParent.left == minNode) {
                minNodeParent.left = minNode.right;
            } else {
                minNodeParent.right = minNode.right;
            }
            treeSize--;
        }
    }


    @Override
    public Iterator<Pair> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<Pair> {
        private Stack<TreeNode> nodeStack = new Stack<>();
        private TreeNode curr = root;

        @Override
        public boolean hasNext() {
            return curr != null || !nodeStack.isEmpty();
        }

        @Override
        public Pair next() {
            if (!hasNext()) throw new NoSuchElementException();

            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.left;
            }

            TreeNode poppedNode = nodeStack.pop();
            Pair pairObj = new Pair(poppedNode.key, poppedNode.val);
            curr = poppedNode.right;

            return pairObj;
        }
    }
}
