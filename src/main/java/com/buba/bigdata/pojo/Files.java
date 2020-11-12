package com.buba.bigdata.exam.pojo;


public class Files {

  private long id;
  private long itemId;
  private String extension;
  private String url;
  private String size;
  private java.sql.Timestamp shoottime;
  private String shootingAd;
  private long idDel;
  private long createdBy;
  private java.sql.Timestamp creationDate;

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }


  public java.sql.Timestamp getShoottime() {
    return shoottime;
  }

  public void setShoottime(java.sql.Timestamp shoottime) {
    this.shoottime = shoottime;
  }


  public String getShootingAd() {
    return shootingAd;
  }

  public void setShootingAd(String shootingAd) {
    this.shootingAd = shootingAd;
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

}
