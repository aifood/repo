package com.poc.ejb.impl;

import javax.ejb.Stateless;

import com.poc.ejb.HelloWorld;
 
@Stateless
public class HelloWorldBean implements HelloWorld {
    public HelloWorldBean() {
    }
 
    public String sayHello() {
    return "Hello World !!!";
    }
}