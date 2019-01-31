package com.campus.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the freetime database table.
 * 
 */
@Entity
@NamedQuery(name="Freetime.findAll", query="SELECT f FROM Freetime f")
public class Freetime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="allow_num")
	private int allowNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="free_date")
	private String freeDate;

	@Column(name="free_note")
	private String freeNote;

	@Column(name="free_state")
	private String freeState;

	@Column(name="owner_id")
	private String ownerId;

	@Column(name="owner_name")
	private String ownerName;

	@Column(name="reserved_num")
	private int reservedNum;

	@Column(name="reserver_id")
	private String reserverId;

	@Column(name="reserver_msg")
	private String reserverMsg;

	@Column(name="reserver_name")
	private String reserverName;

	@Column(name="section_code")
	private String sectionCode;

	@Column(name="section_name")
	private String sectionName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public Freetime() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAllowNum() {
		return this.allowNum;
	}

	public void setAllowNum(int allowNum) {
		this.allowNum = allowNum;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFreeDate() {
		return this.freeDate;
	}

	public void setFreeDate(String freeDate) {
		this.freeDate = freeDate;
	}

	public String getFreeNote() {
		return this.freeNote;
	}

	public void setFreeNote(String freeNote) {
		this.freeNote = freeNote;
	}

	public String getFreeState() {
		return this.freeState;
	}

	public void setFreeState(String freeState) {
		this.freeState = freeState;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getReservedNum() {
		return this.reservedNum;
	}

	public void setReservedNum(int reservedNum) {
		this.reservedNum = reservedNum;
	}

	public String getReserverId() {
		return this.reserverId;
	}

	public void setReserverId(String reserverId) {
		this.reserverId = reserverId;
	}

	public String getReserverMsg() {
		return this.reserverMsg;
	}

	public void setReserverMsg(String reserverMsg) {
		this.reserverMsg = reserverMsg;
	}

	public String getReserverName() {
		return this.reserverName;
	}

	public void setReserverName(String reserverName) {
		this.reserverName = reserverName;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}