package com.mobo.mobolibrary.model;

import java.util.List;

/**
 * 服务端返回的Json消息封装类
 *
 * @param <T>
 * @author sharoncn
 */
public class ResultMessage<T> {
    private List<T> result = null;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


}
