package ru.geekbrains.ads.Lesson_6;

import java.util.Stack;

/**
 * Класс, реализованный на вебинаре
 **/
public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private Node<E> root;
    private int size;
    private final int levels;               // добавил для реализации домашнего задания

    private class NodeAndParent {
        private Node<E> current;
        private Node<E> parent;
        private int level;                  // добавил для реализации домашнего задания

        public NodeAndParent(Node<E> current, Node<E> parent, int currentLevel) {
            this.current = current;
            this.parent = parent;
            this.level = currentLevel;      // добавил для реализации домашнего задания
        }
    }

    public TreeImpl(int levels) {           // добавил для реализации домашнего задания
        this.levels = levels;               // добавил для реализации домашнего задания
    }

    public boolean isTreeBalanced() {       // добавил для реализации домашнего задания
        return isBalanced(root);            // добавил для реализации домашнего задания
    }

    // добавил для реализации домашнего задания
    private boolean isBalanced(Node<E> node) {
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }

    // добавил для реализации домашнего задания
    private int height(Node<E> node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        return nodeAndParent.current != null;
    }

    private NodeAndParent doFind(E value) {
        Node<E> current = root;
        Node<E> parent = null;
        int level = 1;                                  // добавил для реализации домашнего задания

        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, parent, level);
            }

            parent = current;
            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            level++;                                    // добавил для реализации домашнего задания
        }
        return new NodeAndParent(null, parent, level);
    }

    @Override
    public boolean add(E value) {

        Node<E> node = new Node<>(value);

        NodeAndParent nodeAndParent = doFind(value);

        if (nodeAndParent.current != null) {
            return false;
        }
        if (nodeAndParent.level > levels) {         // добавил для реализации домашнего задания
            return false;                           // добавил для реализации домашнего задания
        }

        Node<E> parent = nodeAndParent.parent;

        if (parent == null) {
            root = node;
        } else if (parent.isLeftChild(value)) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }
        size++;

        return true;
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> removedNode = nodeAndParent.current;
        Node<E> parentNode = nodeAndParent.parent;

        if (removedNode == null) {
            return false;
        }

        if (removedNode.isList()) {
            removeLeafNode(removedNode, parentNode);
        } else if (removedNode.hasOnlyOneChild()) {
            removeNodeWithOneChild(removedNode, parentNode);
        } else {
            removeNodeWithAllChildren(removedNode, parentNode);
        }
        size--;
        return true;
    }

    private void removeNodeWithAllChildren(Node<E> removedNode, Node<E> parentNode) {
        Node<E> successor = getSuccessor(removedNode);

        if (removedNode == root) {
            root = successor;
        } else if (parentNode.isLeftChild(removedNode.getValue())) {
            parentNode.setLeftChild(successor);
        } else {
            parentNode.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());

    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }

        return successor;
    }

    private void removeNodeWithOneChild(Node<E> removedNode, Node<E> parentNode) {
        Node<E> childNode = removedNode.getLeftChild() != null
                ? removedNode.getLeftChild()
                : removedNode.getRightChild();

        if (removedNode == root) {
            root = childNode;
        } else if(parentNode.isLeftChild(removedNode.getValue())) {
            parentNode.setLeftChild(childNode);
        } else {
            parentNode.setRightChild(childNode);
        }
    }

    private void removeLeafNode(Node<E> removedNode, Node<E> parentNode) {
        if (removedNode == root) {
            root = null;
        } else if (parentNode.isLeftChild(removedNode.getValue())) {
            parentNode.setLeftChild(null);
        } else {
            parentNode.setRightChild(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public void traverse(TraversMode mode) {
        switch (mode) {
            case PRE_ORDER -> preOrder(root);
            case IN_ORDER -> inOrder(root);
            case POST_ORDER -> postOrder(root);
            default -> throw new RuntimeException("Unknown travers mode: " + mode);
        }
        System.out.println();
    }

    private void postOrder(Node<E> current) { // обратный порядок
        if (current == null) {
            return;
        }

        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.print(current.getValue() + " ");
    }

    private void inOrder(Node<E> current) { //центрированный обход
        if (current == null) {
            return;
        }

        inOrder(current.getLeftChild());
        System.out.print(current.getValue() + " ");
        inOrder(current.getRightChild());
    }

    private void preOrder(Node<E> current) { //прямой обход
        if (current == null) {
            return;
        }

        System.out.print(current.getValue() + " ");
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }
}