package com.cmz.concurrent.design;
class DumbPictureRenderer implements PictureRenderer {
  public Picture render(byte[] raw) {
    return new Picture(new String(raw, raw.length));
  }
}
