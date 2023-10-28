package com.microservices.services;

import com.microservices.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserService {

    private static  ArrayList<User>  users = new ArrayList<>();

    private static AtomicInteger idcounter = new AtomicInteger() ;
    static {

        users.add(new User("freddy",idcounter.incrementAndGet(),10));
        users.add(new User("Chivas",idcounter.incrementAndGet(),15));
        users.add(new User("Jack Danial", idcounter.incrementAndGet(),10));
        users.add(new User("Blonde", idcounter.incrementAndGet(),10));

    }


    public User findOne(int id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(idcounter.incrementAndGet());
        users.add(user);
        return user;
    }

    public boolean  delete(int id){
        return users.removeIf(user -> user.getId()==id);
    }



}
