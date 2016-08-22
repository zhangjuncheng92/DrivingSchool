package com.zjc.drivingschool.eventbus;

import java.util.List;

/**
 * Created by Administrator on 2015/7/14.
 */
public class ActionMultiPhotoEvent {
    private List<String> uris;
    private int action;

    public ActionMultiPhotoEvent(List<String> uris) {
        this.uris = uris;
    }

    public ActionMultiPhotoEvent(List<String> uris, int action) {
        this.uris = uris;
        this.action = action;
    }

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
