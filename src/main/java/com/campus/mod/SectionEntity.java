package com.campus.mod;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "section", schema = "campusdate", catalog = "")
public class SectionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "section_code")
    private String sectionCode;
    @Basic
    @Column(name = "section_name")
    private String sectionName;
    @Basic
    @Column(name = "section_note")
    private String sectionNote;
    @Basic
    @Column(name = "section_start_time")
    private Time sectionStartTime;
    @Basic
    @Column(name = "section_end_time")
    private Time sectionEndTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSectionNote() {
        return sectionNote;
    }

    public void setSectionNote(String sectionNote) {
        this.sectionNote = sectionNote;
    }

    public Time getSectionStartTime() {
        return sectionStartTime;
    }

    public void setSectionStartTime(Time sectionStartTime) {
        this.sectionStartTime = sectionStartTime;
    }

    public Time getSectionEndTime() {
        return sectionEndTime;
    }

    public void setSectionEndTime(Time sectionEndTime) {
        this.sectionEndTime = sectionEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionEntity that = (SectionEntity) o;
        return id == that.id && Objects.equals(sectionCode, that.sectionCode) && Objects.equals(sectionName, that.sectionName) && Objects.equals(sectionNote, that.sectionNote) && Objects.equals(sectionStartTime, that.sectionStartTime) && Objects.equals(sectionEndTime, that.sectionEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sectionCode, sectionName, sectionNote, sectionStartTime, sectionEndTime);
    }
}
