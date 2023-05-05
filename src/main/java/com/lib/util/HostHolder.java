package com.lib.util;

import com.lib.pojo.AllUser;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private ThreadLocal<AllUser> Users = new ThreadLocal<>();

    public void setUsers(AllUser allUser) {
        Users.set(allUser);
    }

    public AllUser getUsers() {
        return Users.get();
    }

    public void clear() {
        Users.remove();
    }
}
