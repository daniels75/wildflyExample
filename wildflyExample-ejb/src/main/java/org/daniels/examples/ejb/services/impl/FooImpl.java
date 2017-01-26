package org.daniels.examples.ejb.services.impl;

import javax.ejb.Singleton;

//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Singleton
public class FooImpl {

    private int a = 10;
    private int b = 1;

    public void incrementA() {
        a +=1;
    }

    public void incrementB() {
        b++;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}