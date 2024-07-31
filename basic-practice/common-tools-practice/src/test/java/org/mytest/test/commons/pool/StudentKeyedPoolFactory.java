package org.mytest.test.commons.pool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StudentKeyedPoolFactory extends BaseKeyedPooledObjectFactory<String, Student> {
    @Override
    public Student create(String key) throws Exception {
        switch(key) {
            case "zs":
                return new Student("zs", 18, "男");
            case "ls":
                return new Student("ls", 19, "女");
            case "ww":
                return new Student("ww", 20, "男");
            case "zl":
                return new Student("zl", 21, "女");
        }
        return null;
    }

    @Override
    public PooledObject<Student> wrap(Student value) {
        return new DefaultPooledObject<>(value);
    }
}
