package com.smart.rpc.spring.config;

/**
 *
 * 提供者
 *
 * @author yangqian
 * @date 2021/4/20
 */
public class ProviderConfig {
    /**
     * 接口
     */
    protected String nozzle;
    /**
     * 映射
     */
    protected String ref;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
