package com.mobo.mobolibrary.model;

import java.util.List;

/**
 * 服务端返回的Json消息封装类
 *
 * @param <T>
 * @author sharoncn
 */
public class ResultMessage<T> {
    private boolean flag;
    private List<T> result = null;// 其他信息
    private Errorinfo errorinfo;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Errorinfo getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(Errorinfo errorinfo) {
        this.errorinfo = errorinfo;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


}
