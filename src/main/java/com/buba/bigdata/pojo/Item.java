package com.buba.bigdata.exam.pojo;


import java.util.List;

public class Item {

  private long id;
  private long deptId;
  private String deptName;
  private long areaId;
  private String areaName;
  private long partyNum;
  private long newClient;
  private long oldClient;
  private long yxClient;
  private long money;
  private long idDel;
  private long createdBy;
  private java.sql.Timestamp creationDate;
  private long modifyBy;
  private java.sql.Timestamp modifyDate;
    private List<Files> fileList;

    public List<Files> getFileList() {
        return fileList;
    }

    public void setFileList(List<Files> fileList) {
        this.fileList = fileList;
    }

    public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDeptId() {
    return deptId;
  }

  public void setDeptId(long deptId) {
    this.deptId = deptId;
  }


  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }


  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
    this.areaId = areaId;
  }


  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }


  public long getPartyNum() {
    return partyNum;
  }

  public void setPartyNum(long partyNum) {
    this.partyNum = partyNum;
  }


  public long getNewClient() {
    return newClient;
  }

  public void setNewClient(long newClient) {
    this.newClient = newClient;
  }


  public long getOldClient() {
    return oldClient;
  }

  public void setOldClient(long oldClient) {
    this.oldClient = oldClient;
  }


  public long getYxClient() {
    return yxClient;
  }

  public void setYxClient(long yxClient) {
    this.yxClient = yxClient;
  }


  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }


  public long getIdDel() {
    return idDel;
  }

  public void setIdDel(long idDel) {
    this.idDel = idDel;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }


  public java.sql.Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(java.sql.Timestamp creationDate) {
    this.creationDate = creationDate;
  }


  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }


  public java.sql.Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

}
