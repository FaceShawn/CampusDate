package com.campus.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the section database table.
 * 
 */
@Entity
@NamedQuery(name="Section.findAll", query="SELECT s FROM Section s")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="section_code")
	private String sectionCode;

	@Column(name="section_end_time")
	private Time sectionEndTime;

	@Column(name="section_name")
	private String sectionName;

	@Column(name="section_note")
	private String sectionNote;

	@Column(name="section_start_time")
	private Time sectionStartTime;

	public Section() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Time getSectionEndTime() {
		return this.sectionEndTime;
	}

	public void setSectionEndTime(Time sectionEndTime) {
		this.sectionEndTime = sectionEndTime;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionNote() {
		return this.sectionNote;
	}

	public void setSectionNote(String sectionNote) {
		this.sectionNote = sectionNote;
	}

	public Time getSectionStartTime() {
		return this.sectionStartTime;
	}

	public void setSectionStartTime(Time sectionStartTime) {
		this.sectionStartTime = sectionStartTime;
	}

}