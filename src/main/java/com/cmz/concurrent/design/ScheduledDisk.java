package com.cmz.concurrent.design;public class ScheduledDisk implements Disk {
  public static final int diskBufferSize = 1; // not really used
  // Use the same scheduler for all requests
  protected static DiskScheduler scheduler = new DiskScheduler();

  public byte[] read(int cyl) {
    byte[] buff = new byte[diskBufferSize];
    DiskTask job = new DiskReadTask(cyl, buff, scheduler);
    job.run();
    return buff;
  }

  public void write(int cyl, byte[] buffer) {
    DiskTask job = new DiskWriteTask(cyl, buffer, scheduler);
    job.run();
  }
}

