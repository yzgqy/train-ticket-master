package contacts.tars.contacts.service;

import contacts.entity.Contacts;
import contacts.tars.contacts.ContactsTars;
import contacts.tars.contacts.ResponseContacts;
import contacts.tars.contacts.ResponseContactsList;
import contacts.tars.contacts.ResponseString;

import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 14:58
 * @Description:
 */
public interface ContactsTarsService {
    ResponseContacts createContacts(ContactsTars contacts);

    ResponseContacts create(ContactsTars addContacts);

    ResponseString delete(UUID contactsId);

    ResponseContacts modify(ContactsTars contacts);

    ResponseContactsList getAllContacts();

    ResponseContacts findContactsById(UUID id);

    ResponseContactsList findContactsByAccountId(UUID accountId);

}
