package org.mytest.test.guava.tree;

import lombok.Data;

import java.util.List;

@Data
public class Node {
    String parentId;
    String id;
    List<Node> childrenList;

    public Node(String id) {
        this.id = id;
    }

    public Node(String parentId, String id, List<Node> childrenList) {
        this.parentId = parentId;
        this.id = id;
        this.childrenList = childrenList;
    }
}
