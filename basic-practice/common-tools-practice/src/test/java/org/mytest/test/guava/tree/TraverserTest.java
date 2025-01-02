package org.mytest.test.guava.tree;

import com.google.common.collect.Lists;
import com.google.common.graph.Traverser;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Traverser 提供了对于图结构的相关处理方法，同时可以兼容树、森林的处理。
 * 注：不直接提供修改、增删节点等操作
 */
public class TraverserTest {
    public static void main(String[] args) {
        Node leaf1 = new Node(null, "leaf1", Lists.newArrayList());
        Node leaf2 = new Node(null, "leaf2", Lists.newArrayList());
        Node leaf3 = new Node(null, "leaf3", Lists.newArrayList());

        Node children1 = new Node(null, "children1", Lists.newArrayList());
        Node children2 = new Node(null, "children2", Lists.newArrayList(leaf1, leaf2));
        Node children3 = new Node(null, "children3", Lists.newArrayList(leaf3));

        Node root = new Node(null, "root", Lists.newArrayList(children1, children2, children3));

        Traverser<Node> nodeTraverser = Traverser.<Node>forTree(Node::getChildrenList);

        // bfs 广度优先
        System.out.println(StreamSupport.stream(nodeTraverser.breadthFirst(root).spliterator(), false)
                .map(Node::getId)
                .collect(Collectors.toList()));
        // dfs 深度优先
        System.out.println(StreamSupport.stream(nodeTraverser.depthFirstPreOrder(root).spliterator(), false)
                .map(Node::getId)
                .collect(Collectors.toList()));
        System.out.println(StreamSupport.stream(nodeTraverser.depthFirstPostOrder(root).spliterator(), false)
                .map(Node::getId)
                .collect(Collectors.toList()));
    }

}
