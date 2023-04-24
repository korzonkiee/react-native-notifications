package com.wix.reactnativenotifications.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.wix.reactnativenotifications.BuildConfig;
import com.wix.reactnativenotifications.core.NotificationIntentAdapter;
import com.wix.reactnativenotifications.core.notification.IPushNotification;
import com.wix.reactnativenotifications.core.notification.PushNotification;

import static com.wix.reactnativenotifications.Defs.LOGTAG;

/**
 * Instance-ID + token refreshing handling service. Contacts the FCM to fetch the updated token.
 *
 * @author amitd
 */
public class FcmInstanceIdListenerService extends FirebaseMessagingService {
  @Override
  public void handleIntent(Intent intent) {
    super.handleIntent(intent);
    
    if (BuildConfig.DEBUG) Log.d(LOGTAG, "New intent: " + intent);

    try {
      final Bundle notificationData = NotificationIntentAdapter.extractPendingNotificationDataFromIntent(intent);
      final IPushNotification pushNotification = PushNotification.get(this, notificationData);
      if (pushNotification != null) {
        pushNotification.onReceived();
      }
    } catch (IPushNotification.InvalidNotificationException e) {
      // An FCM message, yes - but not the kind we know how to work with.
      if (BuildConfig.DEBUG) Log.v(LOGTAG, "FCM message handling aborted", e);
    }
  }
}
