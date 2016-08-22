package com.zjc.drivingschool.eventbus;

/**
 * Created by Administrator on 2015/7/14.
 */
public class ActionCameraEvent {
    private String uri;
    private int action;

    public ActionCameraEvent(String uri) {
        this.uri = uri;
    }

    public ActionCameraEvent(String uri, int action) {
        this.uri = uri;
        this.action = action;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }


}
