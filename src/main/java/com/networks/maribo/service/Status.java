package com.networks.maribo.service;

import com.networks.maribo.LocalContext;
import com.networks.maribo.service.mappers.StatusMapper;

public enum Status {
    DRAFT("Draft"), PROGRESS("InProgress"), DONE("Done");

    private String stat;
    Status(String stat) {}
    public static Status fromString(String stat) {
        Status rl = null;
        if (stat.equals("Draft")) rl = Status.DRAFT;
        if (stat.equals("InProgress")) rl = Status.PROGRESS;
        if (stat.equals("Done")) rl = Status.DONE;
        if (rl != null) {
            rl.stat = stat;
            return rl;
        }
        return null;
    }

    public String getStat() {
        return this.stat;
    }

    public static Status fromId(int id) {
        return LocalContext.runner.query(String.format("SELECT * FROM OrderStatus WHERE id='%s'", id), new StatusMapper()).get(0);
    }
}
