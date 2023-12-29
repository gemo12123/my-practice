package org.mytest.state.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.mytest.state.constant.ActionEnum;
import org.mytest.state.constant.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configurable
@EnableStateMachine
public class StateConfig extends EnumStateMachineConfigurerAdapter<StateEnum, ActionEnum> {

    @Autowired
    private StateMachineListener<StateEnum, ActionEnum> listener;

    /**
     * 初始化当前状态机监听器
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<StateEnum, ActionEnum> config) throws Exception {
        config.withConfiguration().listener(listener);
    }

    /**
     * 初始化当前状态机拥有哪些状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, ActionEnum> states) throws Exception {
        states.withStates()
                .initial(StateEnum.UNSTORED)
                .states(EnumSet.allOf(StateEnum.class));
    }

    /**
     * 初始化当前状态机有哪些状态迁移动作
     * 有来源状态为source，目标状态为target，触发事件为event
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, ActionEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(StateEnum.UNSTORED).target(StateEnum.STORED_ENABLE).event(ActionEnum.SOTRE_AND_OPEN)
                .and().withExternal()
                .source(StateEnum.STORED_DISABLE).target(StateEnum.STORED_ENABLE).event(ActionEnum.UPDATE_AND_OPEN)
                .and().withExternal()
                .source(StateEnum.STORED_ENABLE).target(StateEnum.STORED_DISABLE).event(ActionEnum.UPDATE_AND_UNOPEN)
                .and().withExternal()
                .source(StateEnum.STORED_ENABLE).target(StateEnum.STORED_ENABLE).event(ActionEnum.UPDATE_AND_UPDATE);
    }


//    @Bean
//    public StateMachineListener<StateEnum, ActionEnum> listener() {
//        return new StateMachineListenerAdapter<StateEnum, ActionEnum>() {
////            @Override
////            public void stateChanged(State<StateEnum, ActionEnum> from, State<StateEnum, ActionEnum> to) {
////                System.out.println("stateChanged当前状态为：" + to.getId() + "，转换为：" + to.getId());
////            }
//
//            @Override
//            public void transition(Transition<StateEnum, ActionEnum> transition) {
//                System.out.println("transition当前状态为：" + (transition.getSource() == null ? "未初始化" : transition.getSource().getId()) + "，转换为：" + transition.getTarget().getId());
//            }
//        };
//    }


    /**
     * 持久化到内存map中
     *
     * @return
     */
    @Bean(name = "stateMachineMemPersister")
    public static StateMachinePersister getPersister() {

        return new DefaultStateMachinePersister(new StateMachinePersist() {
            private Map map = new HashMap();
            @Override
            public void write(StateMachineContext context, Object contextObj) throws Exception {
                log.info("持久化状态机,context:{},contextObj:{}", JSON.toJSONString(context), JSON.toJSONString(contextObj));
                map.put(contextObj, context);
            }

            @Override
            public StateMachineContext read(Object contextObj) throws Exception {
                log.info("获取状态机,contextObj:{}", JSON.toJSONString(contextObj));
                StateMachineContext stateMachineContext = (StateMachineContext) map.get(contextObj);
                log.info("获取状态机结果,stateMachineContext:{}", JSON.toJSONString(stateMachineContext));
                return stateMachineContext;
            }
        });
    }

//    @Resource
//    private RedisConnectionFactory redisConnectionFactory;
//
//    /**
//     * 持久化到redis中，在分布式系统中使用
//     *
//     * @return
//     */
//    @Bean(name = "stateMachineRedisPersister")
//    public RedisStateMachinePersister<E, S> getRedisPersister() {
//        RedisStateMachineContextRepository<E, S> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
//        RepositoryStateMachinePersist p = new RepositoryStateMachinePersist<>(repository);
//        return new RedisStateMachinePersister<>(p);
//    }
}
