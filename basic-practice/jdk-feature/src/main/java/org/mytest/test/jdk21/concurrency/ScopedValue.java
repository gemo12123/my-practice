package org.mytest.test.jdk21.concurrency;

/**
 * ScopeValue 不可变线程共享变量
 *
 * @throws Exception
 */
public class ScopedValue {
    public static void main(String[] args) throws Exception {
        scopeValue();
    }

    private static void scopeValue() throws Exception {
        java.lang.ScopedValue<Integer> scopedValue = java.lang.ScopedValue.newInstance();
        java.lang.ScopedValue.Carrier carrier = java.lang.ScopedValue.where(scopedValue, 123);
        carrier.run(() -> System.out.println(scopedValue.get()));
        java.lang.ScopedValue.runWhere(scopedValue, 456, () -> System.out.println(scopedValue.get()));
        // callWhere是阻塞式
        String callResult = java.lang.ScopedValue.callWhere(scopedValue, 789, () -> String.valueOf(scopedValue.get()));
        System.out.println(callResult);
        Thread.sleep(1000);
    }
}
