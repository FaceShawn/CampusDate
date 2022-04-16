package com.campus.mod;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dept", schema = "campusdate", catalog = "")
public class DeptEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "dept_code")
    private String deptCode;
    @Basic
    @Column(name = "dept_name")
    private String deptName;
    @Basic
    @Column(name = "dept_note")
    private String deptNote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNote() {
        return deptNote;
    }

    public void setDeptNote(String deptNote) {
        this.deptNote = deptNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEntity that = (DeptEntity) o;
        return id == that.id && Objects.equals(deptCode, that.deptCode) && Objects.equals(deptName, that.deptName) && Objects.equals(deptNote, that.deptNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptCode, deptName, deptNote);
    }
}
