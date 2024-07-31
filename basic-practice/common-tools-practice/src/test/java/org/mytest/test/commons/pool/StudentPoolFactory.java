package org.mytest.test.commons.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StudentPoolFactory extends BasePooledObjectFactory<Student> {
    @Override
    public Student create() throws Exception {
        return new Student();
    }

    @Override
    public PooledObject<Student> wrap(Student obj) {
        return new DefaultPooledObject<>(obj);
    }


    /**
     *
     * @param p a {@code PooledObject} wrapping the instance to be activated
     *
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<Student> p) throws Exception {
    }

    /**
     * 钝化对象
     * 在对象被归还回池之前调用，用于清空或重置对象状态
     *
     * @param p a {@code PooledObject} wrapping the instance to be passivated
     *
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<Student> p) throws Exception {
        Student obj = p.getObject();
        obj.setName(null);
        obj.setSex(null);
        obj.setAge(0);
    }


}
