// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package contacts.tars.contacts;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class ContactsTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String id = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String accountId = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public String name = "";
	@TarsStructProperty(order = 3, isRequire = false)
	public int documentType = 0;
	@TarsStructProperty(order = 4, isRequire = false)
	public String documentNumber = "";
	@TarsStructProperty(order = 5, isRequire = false)
	public String phoneNumber = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ContactsTars() {
	}

	public ContactsTars(String id, String accountId, String name, int documentType, String documentNumber, String phoneNumber) {
		this.id = id;
		this.accountId = accountId;
		this.name = name;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(id);
		result = prime * result + TarsUtil.hashCode(accountId);
		result = prime * result + TarsUtil.hashCode(name);
		result = prime * result + TarsUtil.hashCode(documentType);
		result = prime * result + TarsUtil.hashCode(documentNumber);
		result = prime * result + TarsUtil.hashCode(phoneNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ContactsTars)) {
			return false;
		}
		ContactsTars other = (ContactsTars) obj;
		return (
			TarsUtil.equals(id, other.id) &&
			TarsUtil.equals(accountId, other.accountId) &&
			TarsUtil.equals(name, other.name) &&
			TarsUtil.equals(documentType, other.documentType) &&
			TarsUtil.equals(documentNumber, other.documentNumber) &&
			TarsUtil.equals(phoneNumber, other.phoneNumber) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != id) {
			_os.write(id, 0);
		}
		if (null != accountId) {
			_os.write(accountId, 1);
		}
		if (null != name) {
			_os.write(name, 2);
		}
		_os.write(documentType, 3);
		if (null != documentNumber) {
			_os.write(documentNumber, 4);
		}
		if (null != phoneNumber) {
			_os.write(phoneNumber, 5);
		}
	}


	public void readFrom(TarsInputStream _is) {
		this.id = _is.readString(0, false);
		this.accountId = _is.readString(1, false);
		this.name = _is.readString(2, false);
		this.documentType = _is.read(documentType, 3, false);
		this.documentNumber = _is.readString(4, false);
		this.phoneNumber = _is.readString(5, false);
	}

}
