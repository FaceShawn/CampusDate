package com.campus.mod;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "freetime", schema = "campusdate", catalog = "")
public class FreetimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "free_date")
    private String freeDate;
    @Basic
    @Column(name = "section_code")
    private String sectionCode;
    @Basic
    @Column(name = "section_name")
    private String sectionName;
    @Basic
    @Column(name = "owner_id")
    private String ownerId;
    @Basic
    @Column(name = "owner_name")
    private String ownerName;
    @Basic
    @Column(name = "free_state")
    private String freeState;
    @Basic
    @Column(name = "reserver_id")
    private String reserverId;
    @Basic
    @Column(name = "reserver_name")
    private String reserverName;
    @Basic
    @Column(name = "allow_num")
    private int allowNum;
    @Basic
    @Column(name = "reserved_num")
    private int reservedNum;
    @Basic
    @Column(name = "free_note")
    private String freeNote;
    @Basic
    @Column(name = "reserver_msg")
    private String reserverMsg;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFreeDate() {
        return freeDate;
    }

    public void setFreeDate(String freeDate) {
        this.freeDate = freeDate;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getFreeState() {
        return freeState;
    }

    public void setFreeState(String freeState) {
        this.freeState = freeState;
    }

    public String getReserverId() {
        return reserverId;
    }

    public void setReserverId(String reserverId) {
        this.reserverId = reserverId;
    }

    public String getReserverName() {
        return reserverName;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public int getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(int allowNum) {
        this.allowNum = allowNum;
    }

    public int getReservedNum() {
        return reservedNum;
    }

    public void setReservedNum(int reservedNum) {
        this.reservedNum = reservedNum;
    }

    public String getFreeNote() {
        return freeNote;
    }

    public void setFreeNote(String freeNote) {
        this.freeNote = freeNote;
    }

    public String getReserverMsg() {
        return reserverMsg;
    }

    public void setReserverMsg(String reserverMsg) {
        this.reserverMsg = reserverMsg;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreetimeEntity that = (FreetimeEntity) o;
        return id == that.id && allowNum == that.allowNum && reservedNum == that.reservedNum && Objects.equals(freeDate, that.freeDate) && Objects.equals(sectionCode, that.sectionCode) && Objects.equals(sectionName, that.sectionName) && Objects.equals(ownerId, that.ownerId) && Objects.equals(ownerName, that.ownerName) && Objects.equals(freeState, that.freeState) && Objects.equals(reserverId, that.reserverId) && Objects.equals(reserverName, that.reserverName) && Objects.equals(freeNote, that.freeNote) && Objects.equals(reserverMsg, that.reserverMsg) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, freeDate, sectionCode, sectionName, ownerId, ownerName, freeState, reserverId, reserverName, allowNum, reservedNum, freeNote, reserverMsg, createTime, updateTime);
    }
}
