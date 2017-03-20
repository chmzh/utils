package com.cmz.concurrent.design;
import java.awt.*;

public interface AutonomousGraphic extends Runnable {
  public void paint(Graphics g);
}
