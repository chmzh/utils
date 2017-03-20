package com.cmz.concurrent.design;
import java.util.*;

public class GroundInventory {
  protected Hashtable items_ = new Hashtable();
  protected Hashtable suppliers_ = new Hashtable();

  protected void store_(String description, Object item,
                        String supplier) {
    items_.put(description, item);
    suppliers_.put(supplier, item);
  }

  protected Object retrieve_(String description) {
    Object x = items_.get(description);
    if (x != null) items_.remove(description);
    return x;
  }
}

