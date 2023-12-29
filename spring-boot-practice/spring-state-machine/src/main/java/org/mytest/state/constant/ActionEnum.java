package org.mytest.state.constant;

public enum ActionEnum {
    /**
     * UNSTORED-->STORED_ENABLE
     */
    SOTRE_AND_OPEN,
    /**
     * STORED_DISABLE-->STORED_ENABLE
     */
    UPDATE_AND_OPEN,
    /**
     * STORED_ENABLE-->STORED_DISABLE
     */
    UPDATE_AND_UNOPEN,
    /**
     * STORED_ENABLE-->STORED_ENABLE
     */
    UPDATE_AND_UPDATE,
}
