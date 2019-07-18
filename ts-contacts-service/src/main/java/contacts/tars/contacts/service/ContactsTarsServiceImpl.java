package contacts.tars.contacts.service;

import contacts.entity.Contacts;
import contacts.repository.ContactsRepository;
import contacts.tars.contacts.ContactsTars;
import contacts.tars.contacts.ResponseContacts;
import contacts.tars.contacts.ResponseContactsList;
import contacts.tars.contacts.ResponseString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 14:58
 * @Description:
 */
@Service
@Slf4j
public class ContactsTarsServiceImpl implements ContactsTarsService{
    @Autowired
    private ContactsRepository contactsRepository;

    @Override
    public ResponseContacts createContacts(ContactsTars contacts) {
        Contacts contactsTemp = contactsRepository.findById(UUID.fromString(contacts.getId()));
        if (contactsTemp != null) {
            System.out.println("[Contacts Service][Init Contacts] Already Exists Id:" + contacts.getId());
            return new ResponseContacts(0, "Already Exists", contactsTemp.toTars());
        } else {
            contactsRepository.save(new Contacts(contacts));
            return new ResponseContacts(1, "Create Success", contactsTemp.toTars());
        }
    }

    @Override
    public ResponseContacts create(ContactsTars addContacts) {
        Contacts contacts = new Contacts();
        contacts.setId(UUID.randomUUID());
        contacts.setName(addContacts.getName());
        contacts.setPhoneNumber(addContacts.getPhoneNumber());
        contacts.setDocumentNumber(addContacts.getDocumentNumber());
        contacts.setAccountId(UUID.fromString(addContacts.getAccountId()));
        contacts.setDocumentType(addContacts.getDocumentType());

        ArrayList<Contacts> accountContacts = contactsRepository.findByAccountId(UUID.fromString(addContacts.getAccountId()));

        if (accountContacts.contains(contacts)) {
            System.out.println("[Contacts-Add&Delete-Service][AddContacts] Fail.Contacts already exists");
            return new ResponseContacts(0, "Contacts already exists", null);
        } else {
            contactsRepository.save(contacts);
            System.out.println("[Contacts-Add&Delete-Service][AddContacts] Success.");
            return new ResponseContacts(1, "Create contacts success", contacts.toTars());
        }
    }

    @Override
    public ResponseString delete(UUID contactsId) {
        contactsRepository.deleteById(contactsId);
        Contacts contacts = contactsRepository.findById(contactsId);
        if (contacts == null) {
            System.out.println("[Contacts-Add&Delete-Service][DeleteContacts] Success.");
            return new ResponseString(1, "Delete success", contactsId.toString());
        } else {
            System.out.println("[Contacts-Add&Delete-Service][DeleteContacts] Fail.Reason not clear.");
            return new ResponseString(0, "Delete failed", contactsId.toString());
        }
    }

    @Override
    public ResponseContacts modify(ContactsTars contacts) {
        ResponseContacts oldContactResponse = findContactsById(UUID.fromString(contacts.getId()));
        log.info(oldContactResponse.toString());
        Contacts oldContacts =new Contacts(oldContactResponse.getData());
        if (oldContacts == null) {
            System.out.println("[Contacts-Modify-Service][ModifyContacts] Fail.Contacts not found.");
            return new ResponseContacts(0, "Contacts not found", oldContacts.toTars());
        } else {
            oldContacts.setName(contacts.getName());
            oldContacts.setDocumentType(contacts.getDocumentType());
            oldContacts.setDocumentNumber(contacts.getDocumentNumber());
            oldContacts.setPhoneNumber(contacts.getPhoneNumber());
            contactsRepository.save(oldContacts);
            System.out.println("[Contacts-Modify-Service][ModifyContacts] Success.");
            return new ResponseContacts(1, "Modify success", oldContacts.toTars());
        }
    }

    @Override
    public ResponseContactsList getAllContacts() {
        ArrayList<Contacts> contacts = contactsRepository.findAll();
        List<ContactsTars> contactsTarsList = new ArrayList<>();
        if (contacts != null && contacts.size() > 0) {
            for(Contacts contact:contacts){
                contactsTarsList.add(contact.toTars());
            }
            return new ResponseContactsList(1, "Success", contactsTarsList);
        } else {
            return new ResponseContactsList(0, "No content", null);
        }
    }

    @Override
    public ResponseContacts findContactsById(UUID id) {
        log.info("FIND CONTACTS BY ID: " + id);
        Contacts contacts = contactsRepository.findById(id);
        if (contacts != null) {
            return new ResponseContacts(1, "Success", contacts.toTars());
        } else {
            return new ResponseContacts(0, "No contacts accorrding to contacts id", null);
        }
    }

    @Override
    public ResponseContactsList findContactsByAccountId(UUID accountId) {
        ArrayList<Contacts> arr = contactsRepository.findByAccountId(accountId);
        List<ContactsTars> contactsTarsList = new ArrayList<>();

        System.out.println("[Contacts-Query-Service][Query-Contacts] Result Size:" + arr.size());
        if (arr != null && arr.size() > 0) {
            for(Contacts contact:arr){
                contactsTarsList.add(contact.toTars());
            }
            return new ResponseContactsList(1, "Success", contactsTarsList);
        } else {
            return new ResponseContactsList(0, "No contacts according to accountId", null);
        }
    }
}
