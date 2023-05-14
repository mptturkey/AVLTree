import java.util.Scanner;

public class AVLTree {

    class Node {
        int element;
        int height;
        Node leftChild;
        Node rightChild;

        public Node() {
            leftChild = null;
            rightChild = null;
            element = 0;
            height = 0;
        }

        public Node(int element) {
            leftChild = null;
            rightChild = null;
            this.element = element;
            height = 0;
        }

    }

    class ConstructAVLTree {
        private Node rootNode;

        public ConstructAVLTree() {
            rootNode = null;
        }

        public void removeAll() {
            rootNode = null;
        }

        public boolean checkEmpty() {
            if (rootNode == null) {
                return true;
            } else {
                return false;
            }
        }

        public void insertElement(int element) {
            rootNode = insertElement(element, rootNode);
        }

        private int getHeight(Node node) {
            return node == null ? -1 : node.height;
        }

        private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
            return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
        }

        private Node insertElement(int element, Node node) {
            if (node == null) {
                node = new Node(element);
            } else if (element < node.element) {
                node.leftChild = insertElement(element, node.leftChild);
                if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2) {
                    if (element < node.leftChild.element) {
                        node = rotateWithLeftChild(node);
                    } else {
                        node = doubleWithLeftChild(node);
                    }
                }
            } else if (element > node.element) {
                node.rightChild = insertElement(element, node.rightChild);
                if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2) {
                    if (element > node.rightChild.element) {
                        node = rotateWithRightChild(node);
                    } else {
                        node = doubleWithRightChild(node);
                    }
                }
            } else ;
            node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
            return node;

        }

        private Node rotateWithLeftChild(Node node2) {
            Node node1 = node2.leftChild;
            node2.leftChild = node1.rightChild;
            node1.rightChild = node2;
            node2.height = getMaxHeight(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
            node1.height = getMaxHeight(getHeight(node1.leftChild), node2.height) + 1;
            return node1;
        }

        private Node rotateWithRightChild(Node node1) {
            Node node2 = node1.rightChild;
            node1.rightChild = node2.leftChild;
            node2.leftChild = node1;
            node1.height = getMaxHeight(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
            node2.height = getMaxHeight(getHeight(node2.rightChild), node1.height) + 1;
            return node2;
        }

        private Node doubleWithLeftChild(Node node) {
            node.leftChild = rotateWithRightChild(node.leftChild);
            return rotateWithLeftChild(node);
        }

        private Node doubleWithRightChild(Node node) {
            node.rightChild = rotateWithLeftChild(node.rightChild);
            return rotateWithRightChild(node);
        }

        public int getTotalNumberOfNodes() {
            return getTotalNumberOfNodes(rootNode);
        }

        private int getTotalNumberOfNodes(Node start) {
            if (start == null) {
                return 0;
            } else {
                int length = 1;
                length = length + getTotalNumberOfNodes(start.leftChild);
                length = length + getTotalNumberOfNodes(start.rightChild);
                return length;
            }
        }

        public boolean searchElement(int element) {
            return searchElement(rootNode, element);
        }

        private boolean searchElement(Node head, int element) {
            boolean check = false;
            while ((head != null) && !check) {
                int headElement = head.element;
                if (element < headElement) {
                    head = head.leftChild;
                } else if (element > headElement) {
                    head = head.rightChild;
                } else {
                    check = true;
                    break;
                }
            }
            return check;
        }

        public void inorderTraversal() {
            inorderTraversal(rootNode);
        }

        private void inorderTraversal(Node head) {
            if (head != null) {
                inorderTraversal(head.leftChild);
                System.out.print(head.element + " ");
                // Operation
                inorderTraversal(head.rightChild);
            }
        }

        public void preorderTraversal() {
            preorderTraversal(rootNode);
        }

        private void preorderTraversal(Node head) {
            if (head != null) {
                System.out.print(head.element + " ");
                preorderTraversal(head.leftChild);
                preorderTraversal(head.rightChild);
            }
        }

        public void postorderTraversal() {
            postorderTraversal(rootNode);
        }

        private void postorderTraversal(Node head) {
            if (head != null) {
                postorderTraversal(head.leftChild);
                postorderTraversal(head.rightChild);
                System.out.print(head.element + " ");
            }
        }


    }

    public class AVLTreeGUI{
        public void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            AVLTree.ConstructAVLTree tree = new AVLTree.ConstructAVLTree();
            char choice;
            do {
                System.out.println("\nSelect an operation:\n");
                System.out.println("1. Insert a node");
                System.out.println("2. Search a node");
                System.out.println("3. Get total number of nodes in AVL Tree");
                System.out.println("4. Is AVL Tree empty?");
                System.out.println("5. Remove all nodes from AVL Tree");
                System.out.println("6. Display AVL Tree in Post order");
                System.out.println("7. Display AVL Tree in Pre order");
                System.out.println("8. Display AVL Tree in In order");

                int ch = sc.nextInt();
                switch (ch)
                {
                    case 1 :
                        System.out.println("Please enter an element to insert in AVLTree");
                        tree.insertElement( sc.nextInt() );
                        break;
                    case 2 :
                        System.out.println("Enter integer element to search");
                        System.out.println(tree.searchElement( sc.nextInt() ));
                        break;
                    case 3 :
                        System.out.println(tree.getTotalNumberOfNodes());
                        break;
                    case 4 :
                        System.out.println(tree.checkEmpty());
                        break;
                    case 5 :
                        tree.removeAll();
                        System.out.println("\nTree Cleared successfully");
                        break;
                    case 6 :
                        System.out.println("\nDisplay AVL Tree in Post order");
                        tree.postorderTraversal();
                        break;
                    case 7 :
                        System.out.println("\nDisplay AVL Tree in Pre order");
                        tree.preorderTraversal();
                        break;
                    case 8 :
                        System.out.println("\nDisplay AVL Tree in In order");
                        tree.inorderTraversal();
                        break;
                    default :
                        System.out.println("\n ");
                        break;
                }
                System.out.println("\nPress 'y' or 'Y' to continue \n" );
                choice = sc.next().charAt(0);
            } while (choice == 'Y' || choice == 'y');
            sc.close();
        }
    }



}