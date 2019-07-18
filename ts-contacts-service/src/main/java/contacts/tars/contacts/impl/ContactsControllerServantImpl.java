package contacts.tars.contacts.impl;

import contacts.tars.contacts.*;
import contacts.tars.contacts.service.ContactsTarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 14:57
 * @Description:
 */
public class ContactsControllerServantImpl implements ContactsControllerServant {
    @Autowired
    private ContactsTarsService contactsTarsService;

    @Override
    public String home() {
        return "Welcome to [ Contacts Service ] !";
    }

    @Override
    public ResponseContactsList getAllContacts() {
        System.out.println("[Contacts Service][Get All Contacts]");
        return contactsTarsService.getAllContacts();
    }

    @Override
    public ResponseContacts createNewContacts(ContactsTars aci) {
        System.out.println("[ContactsService][VerifyLogin] Success");
        return contactsTarsService.create(aci);
    }

    @Override
    public ResponseContacts createNewContactsAdmin(ContactsTars aci) {
        aci.setId(UUID.randomUUID().toString());
        System.out.println("[ContactsService][Create Contacts In Admin]");
        return contactsTarsService.createContacts(aci);
    }

    @Override
    public ResponseString deleteContacts(String contactsId) {
        return contactsTarsService.delete(UUID.fromString(contactsId));
    }

    @Override
    public ResponseContacts modifyContacts(ContactsTars info) {
        System.out.println("[Contacts Service][Modify Contacts] ContactsId:" + info.getId());
        return contactsTarsService.modify(info);
    }

    @Override
    public ResponseContactsList findContactsByAccountId(String accountId) {
        System.out.println("[Contacts Service][Find Contacts By Account Id:" + accountId);
        System.out.println("[ContactsService][VerifyLogin] Success");
        return contactsTarsService.findContactsByAccountId(UUID.fromString(accountId));
    }

    @Override
    public ResponseContacts getContactsByContactsId(String id) {
        System.out.println("[ContactsService][Contacts Id Print] " + id);
        System.out.println("[ContactsService][VerifyLogin] Success.");
        return contactsTarsService.findContactsById(UUID.fromString(id));

    }
}
