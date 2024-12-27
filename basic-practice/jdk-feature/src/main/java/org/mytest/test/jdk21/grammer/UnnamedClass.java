import java.util.ArrayList;
import java.util.List;

void main() {
    List<String> list = new ArrayList<>();
    list.add("apple");
    System.out.println(list);

    print();
}

/**
 * 未命名函数仅能在有void main()声明的文件中声明并使用
 */
public void print(){
    System.out.println("unnamed function is executed!");
}