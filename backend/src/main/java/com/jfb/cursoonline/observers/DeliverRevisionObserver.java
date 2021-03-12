package com.jfb.cursoonline.observers;

import com.jfb.cursoonline.entities.Deliver;

public interface DeliverRevisionObserver {
  
  void onSaveRevision(Deliver deliver);
}
