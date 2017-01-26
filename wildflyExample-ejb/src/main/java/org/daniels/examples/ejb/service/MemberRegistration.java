/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.daniels.examples.ejb.service;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.daniels.examples.ejb.model.Member;
import org.daniels.examples.ejb.services.Foo;
import org.daniels.examples.ejb.services.impl.FooImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;
    
    @EJB
    private FooImpl fooImpl1;
    
    @EJB
    private FooImpl fooImpl2;
    
    

    public void register(Member member) throws Exception {
    	log.info("Ref are the same? " + (fooImpl1 == fooImpl2));
    	log.info("HashCode is the same? " + (fooImpl1.hashCode() == fooImpl2.hashCode()));
    	log.info("Are equals? " + fooImpl1.equals(fooImpl2.hashCode()));
    	
    	log.info("===== INIT ==============");
    	log.info(">>>> INIT fooImpl1 - getA " + fooImpl1.getA());
    	log.info(">>>> INIT fooImpl2 - getA " + fooImpl2.getA());
    	log.info(">>>> INIT fooImpl1 - getB " + fooImpl1.getB());
    	log.info(">>>> INIT fooImpl2 - getB " + fooImpl2.getB());
    	
    	log.info("===== fooImpl1.incrementA() ==============");
    	fooImpl1.incrementA();
    	
    	log.info("===== AFTER fooImpl1.incrementA() ==============");
    	log.info(">>>> fooImpl1 - getA " + fooImpl1.getA());
    	log.info(">>>> fooImpl2 - getA " + fooImpl2.getA());
    	
    	log.info("===== fooImpl2.incrementB() ==============");
    	fooImpl2.incrementB();
    	
    	log.info("===== AFTER fooImpl2.incrementB() ==============");
    	log.info(">>>> fooImpl1 - getB " + fooImpl1.getB());
    	log.info(">>>> fooImpl2 - getB " + fooImpl2.getB());
    	
    	
        log.info("Registering " + member.getName());
        /* from logs
22:50:46,697 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) Ref are the same? false
22:50:46,698 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) HashCode is the same? true
22:50:46,700 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) Are equals? false
22:50:46,701 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) ===== INIT ==============
22:50:46,706 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> INIT fooImpl1 - getA 0
22:50:46,706 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> INIT fooImpl2 - getA 0
22:50:46,707 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> INIT fooImpl1 - getB 0
22:50:46,707 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> INIT fooImpl2 - getB 0
22:50:46,708 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) ===== fooImpl1.incrementA() ==============
22:50:46,708 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) ===== AFTER fooImpl1.incrementA() ==============
22:50:46,709 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> fooImpl1 - getA 1
22:50:46,712 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> fooImpl2 - getA 1
22:50:46,712 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) ===== fooImpl2.incrementB() ==============
22:50:46,714 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) ===== AFTER fooImpl2.incrementB() ==============
22:50:46,715 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> fooImpl1 - getB 1
22:50:46,715 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) >>>> fooImpl2 - getB 1
22:50:46,716 INFO  [org.daniels.examples.ejb.service.MemberRegistration] (default task-3) Registering test
         */
        
        em.persist(member);
        memberEventSrc.fire(member);
    }
}
