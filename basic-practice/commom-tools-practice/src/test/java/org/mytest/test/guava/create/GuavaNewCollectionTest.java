package org.mytest.test.guava.create;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * Guava新集合类型的创建
 */
public class GuavaNewCollectionTest {

    /**
     * HashMultiset使用
     * Multiset是一种Collection类型，类似Map<E, Integer>，键为元素element，值为计数count
     */
    @Test
    public void test01() {
        // Multiset是一种Collection类型
        // 可以理解为Map<E, Integer>，键为元素element，值为计数count
        HashMultiset<String> set = HashMultiset.create();
        set.add("t1");
        set.add("t2");
        set.add("t1");
        // 某个元素的个数
        System.out.println(set.count("t1"));
        // 所有元素个数（包括重复）
        System.out.println(set.size());
        System.out.println("-----------------");
        // entry格式：(element, count)
        Set<Multiset.Entry<String>> entries = set.entrySet();
        for (Multiset.Entry<String> entry : entries) {
            System.out.println(entry.getElement() + " : " + entry.getCount());
        }
        System.out.println("-----------------");
        // 返回所有element
        Set<String> elements = set.elementSet();
        System.out.println(elements);
        System.out.println("-----------------");
        // 增减元素
        set.setCount("t3", 10);
        set.remove("t1", 3);
        set.remove("t4");
        System.out.println(set);
    }

    /**
     * SortedMultiset使用
     * Multiset基础上方便范围查找
     */
    @Test
    public void test02() {
        // SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集。
        TreeMultiset<Integer> set = TreeMultiset.create();
        set.add(1);
        set.add(10);
        set.add(1);
        set.add(15);
        set.add(5);
        set.add(8);
        set.add(5);
        set.add(100);
        // 统计从5-10范围内的元素个数
        // BoundType.CLOSED闭区间（包含），BoundType.OPEN开区间（不包含）
        SortedMultiset<Integer> sub = set.subMultiset(5, BoundType.CLOSED,
                10, BoundType.OPEN);
        System.out.println(sub);
        System.out.println(sub.size());
    }

    /**
     * Multimap 使用
     * Multimap可以很容易地把一个键映射到多个值，类似Map<K, List<V>>
     * 除了不可变形式的实现，其他所有实现都支持null键和null值
     */
    @Test
    public void test03() {
//        ArrayListMultimap<String, String> map = ArrayListMultimap.create();
        HashMultimap<String, String> map = HashMultimap.create();
        map.put("a", "1");
        map.put("a", "2");
        map.put("a", "1");
        map.put("b", "3");
        map.put("b", "4");
        map.put("c", "5");
        System.out.println(map);
//        List<String> a = map.get("a");
        Set<String> a = map.get("a");
        System.out.println(a);
        Map<String, Collection<String>> asMap = map.asMap();
        System.out.println(asMap);
        map.replaceValues("b", Arrays.asList("tt", "yy"));
        System.out.println(map);
        System.out.println("-------------");

        // 获取entry集合，返回Multimap中所有”键-单个值映射”——包括重复键。
        Collection<Map.Entry<String, String>> entries = map.entries();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("------------");

        // 获取key的Multiset
        Multiset<String> keys = map.keys();
        System.out.println(keys);
        // 获取key集合
        Set<String> keys2 = map.keySet();
        System.out.println(keys2);
        // 获取value集合
        Collection<String> values = map.values();
        System.out.println(values);
    }

    /**
     * 双向map
     */
    @Test
    public void test04() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "运维人员");
        biMap.put("2", "开发人员");
        biMap.put("3", "测试人员");

        System.out.println(biMap);
        System.out.println(biMap.inverse());
    }

    /**
     * Table 支持两个键做索引，类似Map<String, Map<String, String>>
     * 常用实现：HashBasedTable、TreeBasedTable、ImmutableTable、ArrayTable
     */
    @Test
    public void test05() {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("zs", "name", "zhangsan");
        table.put("zs", "age", "16");
        table.put("zs", "address", "SD");
        table.put("ls", "name", "lisi");
        table.put("ls", "age", "12");
        table.put("ls", "address", "BJ");

        System.out.println(table.get("ls", "age"));
        System.out.println("-------------------");

        // 用Map<R, Map<C, V>>表现Table<R, C, V>
        Map<String, Map<String, String>> rowMap = table.rowMap();
        System.out.println(rowMap);
        // 返回行的集合
        Set<String> rowKeySet = table.rowKeySet();
        System.out.println(rowKeySet);
        // 返回指定行
        Map<String, String> zs = table.row("zs");
        System.out.println(zs);
        System.out.println("--------------");

        // 用Map<C, Map<R, V>>表现Table<R, C, V>
        Map<String, Map<String, String>> columnMap = table.columnMap();
        System.out.println(columnMap);
        // 返回列的集合
        Set<String> columnKeySet = table.columnKeySet();
        System.out.println(columnKeySet);
        // 返回指定列
        Map<String, String> name = table.column("name");
        System.out.println(name);
        System.out.println("--------------");

        // 返回所有行
        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        for (Table.Cell<String, String, String> cell : cells) {
            System.out.println(cell.getRowKey() + " " + cell.getColumnKey() + " " + cell.getValue());
        }

    }

    /**
     * ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
     * 从技术上讲，ClassToInstanceMap<B>实现了Map<Class<? extends B>, B>
     * 有两种实现：MutableClassToInstanceMap 和 ImmutableClassToInstanceMap
     */
    @Test
    public void test06(){
        ClassToInstanceMap<Number> map=MutableClassToInstanceMap.create();
        map.putInstance(Integer.class, Integer.valueOf(0));
        map.putInstance(Integer.class, Integer.valueOf(1));

        Number number = map.get(Integer.class);
        System.out.println(number);
    }

    /**
     * RangeSet描述了一组不相连的、非空的区间。
     * 当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。
     */
    @Test
    public void test07(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

        rangeSet.add(Range.open(100,110));
        rangeSet.add(Range.open(110,120));
        System.out.println(rangeSet);

        // 补集
        System.out.println(rangeSet.complement());
        // 交集
        System.out.println(rangeSet.subRangeSet(Range.open(95, 102)));
        // 获取set
        Set<Range<Integer>> ranges = rangeSet.asRanges();
        System.out.println(ranges);
        // 判断区间是否包含
        System.out.println(rangeSet.contains(105));
        System.out.println(rangeSet.contains(120));
        // 找出给定数值的所在区间（如果有的话）
        System.out.println(rangeSet.rangeContaining(105));
        System.out.println(rangeSet.rangeContaining(120));
        // 判断RangeSet中是否有任何区间包括给定区间
        System.out.println(rangeSet.encloses(Range.open(15,18)));
        // 所有区间的最小区间
        System.out.println(rangeSet.span());
    }

    /**
     * RangeMap描述了”不相交的、非空的区间”到特定值的映射。
     * 和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。
     */
    @Test
    public void test08(){
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        // 移除范围
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
        System.out.println(rangeMap);

        // 返回map
        Map<Range<Integer>, String> mapOfRanges = rangeMap.asMapOfRanges();
        System.out.println(mapOfRanges);
        // 交集
        RangeMap<Integer, String> subRangeMap = rangeMap.subRangeMap(Range.open(2, 4));
        System.out.println(subRangeMap);
    }
}
