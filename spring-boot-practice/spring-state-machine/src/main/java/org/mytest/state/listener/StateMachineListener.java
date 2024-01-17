package org.mytest.state.listener;

import org.mytest.state.constant.ActionEnum;
import org.mytest.state.constant.StateEnum;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
public class StateMachineListener extends StateMachineListenerAdapter<StateEnum, ActionEnum> {

    @Override
    public void transition(Transition<StateEnum, ActionEnum> transition) {
        // 业务处理
        System.out.println("transition当前状态为：" + (transition.getSource() == null ? "未初始化" : transition.getSource().getId()) + "，转换为：" + transition.getTarget().getId());
//        if (new Random().nextBoolean()) {
//            throw new RuntimeException();
//        }
    }
}
