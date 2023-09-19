#import <UIKit/UIKit.h>
#import <PushKit/PushKit.h>
#import <UserNotifications/UserNotifications.h>
#import "RNNotificationCenterMulticast.h"

@interface RNNotifications : NSObject

+ (instancetype)sharedInstance;

+ (void)startMonitorNotifications;
+ (void)startMonitorPushKitNotifications;

+ (void)didReceiveForegroundNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions))completionHandler;
+ (void)didReceiveNotificationResponse:(UNNotificationResponse *)response completionHandler:(void (^)(void))completionHandler;
+ (void)didReceiveBackgroundNotification:(NSDictionary *)userInfo withCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler;

+ (void)didRegisterForRemoteNotificationsWithDeviceToken:(id)deviceToken;
+ (void)didFailToRegisterForRemoteNotificationsWithError:(NSError *)error;

+ (void)addNativeDelegate:(id<UNUserNotificationCenterDelegate>)delegate;
+ (void)removeNativeDelegate:(id<UNUserNotificationCenterDelegate>)delegate;

- (RNNotificationCenterMulticast*)multicast;

@end
