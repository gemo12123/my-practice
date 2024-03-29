package org.mytest.test.guava.behavior;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;
import org.mytest.test.guava.create.GuavaImmutableSetTest;

import java.util.*;

public class CollectionsTest {

    /**
     * Sets
     */
    @Test
    public void test01() {
        HashSet<String> name1 = Sets.newHashSet("zs", "ls", "ww");
        HashSet<String> name2 = Sets.newHashSet("zw", "tt", "zs");

        // 并集
        Sets.SetView<String> union = Sets.union(name1, name2);
        System.out.println(union);
        // 交集
        Sets.SetView<String> intersection = Sets.intersection(name1, name2);
        System.out.println(intersection);
        // 差集
        Sets.SetView<String> difference1 = Sets.difference(name1, name2);
        Sets.SetView<String> difference2 = Sets.difference(name2, name1);
        System.out.println(difference1);
        System.out.println(difference2);
        // 对称差值，只在一个集合中出现的元素
        Sets.SetView<String> symmetricDifference = Sets.symmetricDifference(name1, name2);
        System.out.println(symmetricDifference);
        // 笛卡尔积
        Set<List<String>> cartesianProduct1 = Sets.cartesianProduct(name1, name2);
        Set<List<String>> cartesianProduct2 = Sets.cartesianProduct(name2, name1);
        System.out.println(cartesianProduct1);
        System.out.println(cartesianProduct2);
        System.out.println("--------------");
        // 子集，一个集合的所有可能子集
        Set<Set<String>> powerSet = Sets.powerSet(name1);
        for (Set<String> set : powerSet) {
            System.out.println(set);
        }
        System.out.println(powerSet);
    }

    /**
     * Maps
     */
    @Test
    public void test02() {
        GuavaImmutableSetTest.Student zs = new GuavaImmutableSetTest.Student("zs", 10);
        GuavaImmutableSetTest.Student ls = new GuavaImmutableSetTest.Student("ls", 10);
        List<GuavaImmutableSetTest.Student> list = Arrays.asList(zs, ls);
        // 从集合中按照唯一的属性值查找对象，相同功能：stream.collect(Collectors.toMap(对象::get唯一属性, item->item))
        ImmutableMap<String, GuavaImmutableSetTest.Student> uniqueIndex = Maps.uniqueIndex(list, GuavaImmutableSetTest.Student::getName);
        System.out.println(uniqueIndex.get("zs"));

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "zs");
        map1.put("age", "14");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "ls");
        map2.put("age", "14");
        map2.put("address", "SD");

        // 两个Map的不同点
        MapDifference<String, String> difference = Maps.difference(map1, map2);
        System.out.println(difference.areEqual());
        System.out.println(difference);
        // 键相同但是值不同值映射项
        Map<String, MapDifference.ValueDifference<String>> entriesDiffering = difference.entriesDiffering();
        System.out.println(entriesDiffering);
        // 只有左边有
        Map<String, String> onlyOnRight = difference.entriesOnlyOnRight();
        System.out.println(onlyOnRight);
        // 只有右边有
        Map<String, String> onlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println(onlyOnLeft);
    }

    /**
     * Iterables
     */
    @Test
    public void test03() {
        List<String> list = Arrays.asList("zs", "ls", "ww", "zs");
        // 返回集合指定元素出现次数
        System.out.println(Collections.frequency(list, "zs"));
        System.out.println(Iterables.frequency(list, "zs"));
        // 切分，得到的子集都不能进行修改操作
        Iterable<List<String>> partition = Iterables.partition(list, 3);
        System.out.println(partition);
    }

    /**
     * Multimaps
     */
    @Test
    public void test04() {
        // Multimaps.index(Iterable, Function)类似于Maps.uniqueIndex(Iterable, Function)
        // 用于一组对象有共同的特定属性，按照这个属性的值查询对象，但属性值不一定是独一无二的
        List<String> list = Arrays.asList("zss", "ls", "www", "dmlw");
        ImmutableListMultimap<Integer, String> index = Multimaps.index(list, String::length);
        System.out.println(index.get(3));

        // 反转操作
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));

        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(inverse);
    }

    /**
     * Tables
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
        System.out.println(table);
        // 将Table<C, R, V>转置成Table<R, C, V>
        System.out.println(Tables.transpose(table));
        // 将每个值都进行函数转换
        System.out.println(Tables.transformValues(table, t -> t + "_test"));
    }
}
