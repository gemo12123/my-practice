package org.mytest.test.guava.tree;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;

public class GraphTest {
    public static void main(String[] args) {
        // 创建组织节点
        // 这里纠结使用 ceo 还是 ceo？有没有人给出自己的理解？
        Node ceo = new Node("CEO");
        Node cto = new Node("CTO");
        Node cfo = new Node("CFO");
        Node devLead = new Node("Dev Lead");
        Node dev1 = new Node("Developer 1");
        Node dev2 = new Node("Developer 2");
        Node financeLead = new Node("Finance Lead");
        Node accountant1 = new Node("Accountant 1");
        Node accountant2 = new Node("Accountant 2");

        // 使用 GraphBuilder 构建组织架构的下属关系（从上级指向下级）
        ImmutableGraph<Node> organizationGraph = GraphBuilder.directed()    // 树是有方向的（从上级指向下级）
                .<Node>immutable()
                .putEdge(ceo, cto)          // CEO -> CTO
                .putEdge(ceo, cfo)          // CEO -> CFO
                .putEdge(cto, devLead)      // CTO -> Dev Lead
                .putEdge(devLead, dev1)     // Dev Lead -> Developer 1
                .putEdge(devLead, dev2)     // Dev Lead -> Developer 2
                .putEdge(cfo, financeLead)  // CFO -> Finance Lead
                .putEdge(financeLead, accountant1)  // Finance Lead -> Accountant 1
                .putEdge(financeLead, accountant2)  // Finance Lead -> Accountant 2
                .build();

        // 遍历和显示节点关系
        System.out.println("Organization Structure (Subordinate Relationships):");
        organizationGraph.nodes()
                .forEach(node -> {
                    System.out.println(node + " has subordinates: ");
                    organizationGraph.successors(node)
                            .forEach(successor -> System.out.println("  -> " + successor));
                });
    }

}
