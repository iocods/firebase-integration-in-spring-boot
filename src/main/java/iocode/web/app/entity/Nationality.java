package iocode.web.app.entity;

public enum Nationality {
  UNITED_STATE("American"), NIGERIAN("Nigerian"),
  SINGAPORE("Singaporean"), CHINA("Chinese"),
  SOUTH_AFRICAN("South African"), KENYA("Kenyan"),
  BRAZIL("Brazilian"), GHANA("Ghanaian"),
  ISRAEL("Israeli"), INDIA("Indian");

  private final String displayName;

  Nationality(String displayName) {
    this.displayName = displayName;
  }
  public String toString() {
    return this.displayName;
  }
}
