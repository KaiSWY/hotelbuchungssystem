package com.hotelbooking.repository;

import com.hotelbooking.model.ContactPerson;
import org.hibernate.SessionFactory;

public class ContactPersonRepository extends Repository<ContactPerson, Integer>
{
    public ContactPersonRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, ContactPerson.class);
    }
}
