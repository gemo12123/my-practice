package org.mytest.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mytest.state.entity.Supervise;
import org.mytest.state.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StateTest {
    @Autowired
    private StateService stateService;

    @Before
    public void before() throws Exception {
        Supervise newSupervise = new Supervise();
        newSupervise.setId("1");
        newSupervise.setPeriod(4);
        Supervise oldSupervise = null;

        stateService.save(newSupervise, oldSupervise);
    }

    @Test
    public void test01() throws Exception {
        Supervise newSupervise = new Supervise();
        newSupervise.setId("1");
        newSupervise.setPeriod(8);
        Supervise oldSupervise = new Supervise();
        oldSupervise.setId("1");
        oldSupervise.setPeriod(null);

        stateService.save(newSupervise, oldSupervise);
    }

    @Test
    public void test02() throws Exception {
        stateService.safe("1");
    }
}
