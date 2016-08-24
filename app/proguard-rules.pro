# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Mr_ZJC\develop\android\ide\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes Signature

-ignorewarnings

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }


-assumenosideeffects class android.util.Log {
           public static boolean isLoggable(java.lang.String, int);
           public static int v(...);
           public static int i(...);
           public static int w(...);
           public static int d(...);
           public static int e(...);
       }

-dontwarn android.provider.**
-keep class **$Properties

-keep public class com.mobo.mediclapartner.R$*{
          public static final int *;
       }

-keep class com.zjc.drivingschool.db.** { *; }
-keep class com.mobo.mobolibrary.model.** { *; }
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

# 添加第三方jar包

#网络请求混淆包
-keep class org.apache.** { *; }

-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.**{*;}

-keep class org.json.** { *; }
-dontwarn org.json.**

-keep class cn.jpush.** { *; }
-dontwarn cn.jpush.**


-keep class com.loopj.android.** { *; }
-keep interface com.loopj.android.** { *; }

-keep class com.google.gson.**{*;}

-dontwarn com.squareup.okhttp.**

-keep  class freemarker.template.**{*;}

-dontwarn de.greenrobot.daogenerator.**

-dontwarn in.srain.cube.views.ptr.**

-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.**{*;}

-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}

-keepclassmembers class ** {
    public void onEvent*(**);
}

#支付宝
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

#支付宝 end

#umeng updata start
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
#umeng updata end

#umengshare start
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class com.mobo.mediclapartner.ui.shopping.ShoppingHeathCardDetailFragment { *; }

-keepclassmembers class com.mobo.mediclapartner.ui.shopping.ShoppingHeathCardDetailFragment$WebAppInterface {
  public *;
}

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
#umengshare end


#BeeCloud
-keep class cn.beecloud.** { *; }
-keep class com.google.** { *; }
#支付宝
-keep class com.alipay.** { *; }
#微信
-keep class com.tencent.** { *; }
#银联
-keep class com.unionpay.** { *; }
#百度
-keep class com.baidu.** { *; }
-keep class com.dianxinos.** { *; }

#Android Studio中包含PayPal依赖，需要添加
-dontwarn com.paypal.**
-dontwarn io.card.payment.**
-dontwarn okhttp3.**
-dontwarn okio.**

-keep class com.paypal.** { *; }
-keep class io.card.payment.** { *; }

-keep interface okhttp3.** { *; }
-keep interface okio.** { *; }

-keep class okhttp3.** { *; }
-keep class okio.** { *; }
#BeeCloud end