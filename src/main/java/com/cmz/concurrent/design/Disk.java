package com.cmz.concurrent.design;
public interface Disk {
  public byte[] read(int cylinderNumber);
  public void write(int cylinderNumber, byte[] buffer);
}

