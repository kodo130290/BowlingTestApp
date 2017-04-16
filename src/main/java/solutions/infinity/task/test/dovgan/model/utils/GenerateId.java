package main.java.solutions.infinity.task.test.dovgan.model.utils;

import java.rmi.server.UID;

public class GenerateId {

    public static Integer generateId(){
        UID id = new UID();
        return  id.hashCode();
    }
}
