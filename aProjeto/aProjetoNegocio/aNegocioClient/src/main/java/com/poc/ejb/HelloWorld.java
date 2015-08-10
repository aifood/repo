package com.poc.ejb;

import javax.ejb.Remote;
 
@Remote
public interface HelloWorld {
    public String sayHello();
}