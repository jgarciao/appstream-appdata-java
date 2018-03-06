package org.freedesktop.appstream;

public class ReleaseInfo{

  String version;
  int timestamp;
  String description;


  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ReleaseInfo(String version, int timestamp, String description) {
    this.version = version;
    this.timestamp = timestamp;
    this.description = description;
  }
}
