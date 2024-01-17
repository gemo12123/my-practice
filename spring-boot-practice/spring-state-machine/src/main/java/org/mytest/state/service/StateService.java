package org.mytest.state.service;

import org.mytest.state.constant.ActionEnum;
import org.mytest.state.constant.StateEnum;
import org.mytest.state.entity.Supervise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    @Autowired
    private StateMachine<StateEnum, ActionEnum> stateMachine;
    @Autowired
    private StateMachinePersister<StateEnum, ActionEnum, String> stateMachineMemPersister;

    public void save(Supervise supervise, Supervise oldSupervise) throws Exception {
        String id = supervise.getId();
        // 根据id获取旧的周期
        if (oldSupervise == null) {
            // UNSTORED
            stateMachine.start();
            // UNSTORED-->STORED_ENABLE
            Message<ActionEnum> message = MessageBuilder.withPayload(ActionEnum.SOTRE_AND_OPEN)
                    .setHeader("supervise", supervise)
                    .build();
            stateMachine.sendEvent(message);
            stateMachineMemPersister.persist(stateMachine, id);
            return;
        }


        stateMachineMemPersister.restore(stateMachine, id);
        if (oldSupervise.getPeriod() !=null && supervise.getPeriod()==null) {
            // STORED_DISABLE-->STORED_ENABLE
            Message<ActionEnum> message = MessageBuilder.withPayload(ActionEnum.UPDATE_AND_OPEN)
                    .setHeader("supervise", supervise)
                    .build();
            stateMachine.sendEvent(message);
            stateMachineMemPersister.persist(stateMachine, id);
            return;
        }

        if(oldSupervise.getPeriod() == null && supervise.getPeriod() != null){
            // STORED_ENABLE-->STORED_DISABLE
            Message<ActionEnum> message = MessageBuilder.withPayload(ActionEnum.UPDATE_AND_UNOPEN)
                    .setHeader("supervise", supervise)
                    .build();
            stateMachine.sendEvent(message);
            stateMachineMemPersister.persist(stateMachine, id);
            return;
        }

        // 修改周期
        // STORED_ENABLE-->STORED_ENABLE
        Message<ActionEnum> message = MessageBuilder.withPayload(ActionEnum.UPDATE_AND_UPDATE)
                .setHeader("supervise", supervise)
                .build();
        stateMachine.sendEvent(message);
        stateMachineMemPersister.persist(stateMachine, id);
    }

    public void safe(String id) throws Exception {
        stateMachine.start();
        stateMachineMemPersister.restore(stateMachine, id);
        new Thread(()->{
            try {
                Thread.sleep(1000);
                stateMachineMemPersister.restore(stateMachine, id);
                System.out.println(Thread.currentThread().getName()+"===="+stateMachine.getState().getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"test-thread").start();
        Thread.sleep(5*1000);
        System.out.println(Thread.currentThread().getName()+"===="+stateMachine.getState().getId());
    }
}
