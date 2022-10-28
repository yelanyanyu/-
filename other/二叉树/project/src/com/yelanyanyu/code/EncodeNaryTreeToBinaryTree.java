package com.yelanyanyu.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class EncodeNaryTreeToBinaryTree {
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //提交这个类
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode treeRoot = new TreeNode(root.val);
            TreeNode leftTreeRoot = en(root.children);
            treeRoot.left = leftTreeRoot;
            return treeRoot;
        }

        /**
         * 构建左子树
         *
         * @param children
         * @return
         */
        private TreeNode en(List<Node> children) {
            if (children == null) {
                return null;
            }
            TreeNode head = null;
            TreeNode cur = null;
            TreeNode pre = null;
            for (int i = 1; i < children.size(); i++) {
                cur = new TreeNode(children.get(i).val);
                pre = new TreeNode(children.get(i - 1).val);
                if (head == null) {
                    head = cur;
                } else {
                    pre.right = cur;
                    cur.left = en(children.get(i).children);
                }
            }
            return head;
        }

//        private TreeNode en(List<Node> children) {
//            TreeNode head = null;
//            TreeNode cur = null;
//            for (Node child : children) {
//                TreeNode tNode = new TreeNode(child.val);
//                if (head == null) {
//                    head = tNode;
//                } else {
//                    cur.right = tNode;
//                }
//                cur = tNode;
//                cur.left = en(child.children);
//            }
//            return head;
//        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node nodeRoot = new Node(root.val, de(root.left));
            return nodeRoot;
        }

        /**
         * 返回当前节点cur的全部孩子序列
         *
         * @return
         */
        public List<Node> de(TreeNode cur) {
            ArrayList<Node> children = new ArrayList<>();
            while (cur != null) {
                Node node = new Node(cur.val, de(cur.left));
                children.add(node);
                cur = cur.right;
            }
            return children;
        }
    }
}
