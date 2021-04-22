package com.smart.rpc.spring.config;

/**
 *
 * 消费者
 *
 * @author yangqian
 * @date 2021/4/20
 */
public class ConsumerConfig {
    /**
     * 接口
     */
    protected String nozzle;
    /**
     * 别名
     */
    protected String alias;

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
