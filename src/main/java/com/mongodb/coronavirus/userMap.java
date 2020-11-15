package com.mongodb.coronavirus;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class userMap {

    public Map<String,MessageReceivedEvent> users;

    public userMap(){
        users = new HashMap<>();
    }

    public void addLastMessage(String k, MessageReceivedEvent e){
        if(!users.containsKey(k)) {
            //System.out.println("User added to Map");
            users.put(k, e);
        }
        else{
            //System.out.println("Last message updated");
            //System.out.println("Previous message was sent @ " + users.get(k).getMessage().getCreationTime());
            users.replace(k,e);
        }
    }
    public MessageReceivedEvent getLastMessage(String k){
        return users.get(k);
    }
}
