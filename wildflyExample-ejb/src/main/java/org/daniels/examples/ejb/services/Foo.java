package org.daniels.examples.ejb.services;

import javax.ejb.Local;

@Local
public interface Foo {
	void incrementA();
	void incrementB();
	int getA();
	int getB();
}
