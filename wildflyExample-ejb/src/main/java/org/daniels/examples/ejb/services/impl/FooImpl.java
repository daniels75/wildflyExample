package org.daniels.examples.ejb.services.impl;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

import org.daniels.examples.ejb.services.Foo;

//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Singleton
public class FooImpl {

    private int valueA = 0;
    private int valueB = 0;

    public void incrementA() {
    	valueA +=1;
    }

    public void incrementB() {
    	valueB++;
    }

    public int getA() {
        return valueA;
    }

    public int getB() {
        return valueB;
    }
}