// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package security.tars.rpc.order;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class OrderTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String id = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public long boughtDate = 0;
	@TarsStructProperty(order = 2, isRequire = false)
	public long travelDate = 0;
	@TarsStructProperty(order = 3, isRequire = false)
	public long travelTime = 0;
	@TarsStructProperty(order = 4, isRequire = false)
	public String accountId = "";
	@TarsStructProperty(order = 5, isRequire = false)
	public String contactsName = "";
	@TarsStructProperty(order = 6, isRequire = false)
	public int documentType = 0;
	@TarsStructProperty(order = 7, isRequire = false)
	public String contactsDocumentNumber = "";
	@TarsStructProperty(order = 8, isRequire = false)
	public String trainNumber = "";
	@TarsStructProperty(order = 9, isRequire = false)
	public int coachNumber = 0;
	@TarsStructProperty(order = 10, isRequire = false)
	public int seatClass = 0;
	@TarsStructProperty(order = 11, isRequire = false)
	public String seatNumber = "";
	@TarsStructProperty(order = 12, isRequire = false)
	public String from = "";
	@TarsStructProperty(order = 13, isRequire = false)
	public String to = "";
	@TarsStructProperty(order = 14, isRequire = false)
	public int status = 0;
	@TarsStructProperty(order = 15, isRequire = false)
	public String price = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getBoughtDate() {
		return boughtDate;
	}

	public void setBoughtDate(long boughtDate) {
		this.boughtDate = boughtDate;
	}

	public long getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(long travelDate) {
		this.travelDate = travelDate;
	}

	public long getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(long travelTime) {
		this.travelTime = travelTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String getContactsDocumentNumber() {
		return contactsDocumentNumber;
	}

	public void setContactsDocumentNumber(String contactsDocumentNumber) {
		this.contactsDocumentNumber = contactsDocumentNumber;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getCoachNumber() {
		return coachNumber;
	}

	public void setCoachNumber(int coachNumber) {
		this.coachNumber = coachNumber;
	}

	public int getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(int seatClass) {
		this.seatClass = seatClass;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public OrderTars() {
	}

	public OrderTars(String id, long boughtDate, long travelDate, long travelTime, String accountId, String contactsName, int documentType, String contactsDocumentNumber, String trainNumber, int coachNumber, int seatClass, String seatNumber, String from, String to, int status, String price) {
		this.id = id;
		this.boughtDate = boughtDate;
		this.travelDate = travelDate;
		this.travelTime = travelTime;
		this.accountId = accountId;
		this.contactsName = contactsName;
		this.documentType = documentType;
		this.contactsDocumentNumber = contactsDocumentNumber;
		this.trainNumber = trainNumber;
		this.coachNumber = coachNumber;
		this.seatClass = seatClass;
		this.seatNumber = seatNumber;
		this.from = from;
		this.to = to;
		this.status = status;
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(id);
		result = prime * result + TarsUtil.hashCode(boughtDate);
		result = prime * result + TarsUtil.hashCode(travelDate);
		result = prime * result + TarsUtil.hashCode(travelTime);
		result = prime * result + TarsUtil.hashCode(accountId);
		result = prime * result + TarsUtil.hashCode(contactsName);
		result = prime * result + TarsUtil.hashCode(documentType);
		result = prime * result + TarsUtil.hashCode(contactsDocumentNumber);
		result = prime * result + TarsUtil.hashCode(trainNumber);
		result = prime * result + TarsUtil.hashCode(coachNumber);
		result = prime * result + TarsUtil.hashCode(seatClass);
		result = prime * result + TarsUtil.hashCode(seatNumber);
		result = prime * result + TarsUtil.hashCode(from);
		result = prime * result + TarsUtil.hashCode(to);
		result = prime * result + TarsUtil.hashCode(status);
		result = prime * result + TarsUtil.hashCode(price);
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
		if (!(obj instanceof OrderTars)) {
			return false;
		}
		OrderTars other = (OrderTars) obj;
		return (
			TarsUtil.equals(id, other.id) &&
			TarsUtil.equals(boughtDate, other.boughtDate) &&
			TarsUtil.equals(travelDate, other.travelDate) &&
			TarsUtil.equals(travelTime, other.travelTime) &&
			TarsUtil.equals(accountId, other.accountId) &&
			TarsUtil.equals(contactsName, other.contactsName) &&
			TarsUtil.equals(documentType, other.documentType) &&
			TarsUtil.equals(contactsDocumentNumber, other.contactsDocumentNumber) &&
			TarsUtil.equals(trainNumber, other.trainNumber) &&
			TarsUtil.equals(coachNumber, other.coachNumber) &&
			TarsUtil.equals(seatClass, other.seatClass) &&
			TarsUtil.equals(seatNumber, other.seatNumber) &&
			TarsUtil.equals(from, other.from) &&
			TarsUtil.equals(to, other.to) &&
			TarsUtil.equals(status, other.status) &&
			TarsUtil.equals(price, other.price) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != id) {
			_os.write(id, 0);
		}
		_os.write(boughtDate, 1);
		_os.write(travelDate, 2);
		_os.write(travelTime, 3);
		if (null != accountId) {
			_os.write(accountId, 4);
		}
		if (null != contactsName) {
			_os.write(contactsName, 5);
		}
		_os.write(documentType, 6);
		if (null != contactsDocumentNumber) {
			_os.write(contactsDocumentNumber, 7);
		}
		if (null != trainNumber) {
			_os.write(trainNumber, 8);
		}
		_os.write(coachNumber, 9);
		_os.write(seatClass, 10);
		if (null != seatNumber) {
			_os.write(seatNumber, 11);
		}
		if (null != from) {
			_os.write(from, 12);
		}
		if (null != to) {
			_os.write(to, 13);
		}
		_os.write(status, 14);
		if (null != price) {
			_os.write(price, 15);
		}
	}


	public void readFrom(TarsInputStream _is) {
		this.id = _is.readString(0, false);
		this.boughtDate = _is.read(boughtDate, 1, false);
		this.travelDate = _is.read(travelDate, 2, false);
		this.travelTime = _is.read(travelTime, 3, false);
		this.accountId = _is.readString(4, false);
		this.contactsName = _is.readString(5, false);
		this.documentType = _is.read(documentType, 6, false);
		this.contactsDocumentNumber = _is.readString(7, false);
		this.trainNumber = _is.readString(8, false);
		this.coachNumber = _is.read(coachNumber, 9, false);
		this.seatClass = _is.read(seatClass, 10, false);
		this.seatNumber = _is.readString(11, false);
		this.from = _is.readString(12, false);
		this.to = _is.readString(13, false);
		this.status = _is.read(status, 14, false);
		this.price = _is.readString(15, false);
	}

}
