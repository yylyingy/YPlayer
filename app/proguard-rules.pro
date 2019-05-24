-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-keepattributes SourceFile,LineNumberTable

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn android.support.v4.**
-dontwarn android.webkit.WebView

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}


# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.

-dontwarn android.support.**
-keep class android.support.** { *;}
-keep interface android.support.** { *;}

-dontwarn **.bean.**
-keep class **.bean.** { *;}

-dontwarn com.paisheng.commonlib.network.**
-keep class com.paisheng.commonlib.network.** { *;}

-dontwarn com.paisheng.commonbiz.network.**
-keep class com.paisheng.commonbiz.network.** { *;}

## ----------------------------------
##      UIL相关
## ----------------------------------
-keep class com.nostra13.universalimageloader.** { *; }
-keepclassmembers class com.nostra13.universalimageloader.** {*;}
-dontwarn com.nostra13.universalimageloader.**

-dontwarn eu.inmite.android.lib.dialogs.**
-keep class eu.inmite.android.lib.dialogs.** { *;}

-dontwarn com.loopj.android.http.**
-keep class com.loopj.android.http.** { *;}

-dontwarn com.viewpagerindicator.**
-keep class com.viewpagerindicator.** { *;}
-keep interface com.actionbarsherlock.** { *;}

-dontwarn kankan.wheel.widget.**
-keep class kankan.wheel.widget.** { *;}

-dontwarn com.handmark.pulltorefresh.**
-keep class com.handmark.pulltorefresh.** { *;}

-dontwarn com.paisheng.lib.webviewlib.**
-keep class com.paisheng.lib.webviewlib.** { *;}

-dontwarn com.github.**
-keep class com.github.** { *;}

-dontwarn com.nhaarman.**
-keep class com.nhaarman.** { *;}

-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *;}

-dontoptimize
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontpreverify
-verbose

-dontwarn class androidx.fragment.app.**
-dontwarn class androidx.appcompat.app.**
-dontwarn android.support.v4.**
-keep class android.support.v4.** {
    <fields>;
    <methods>;
}
-keep interface  android.support.v4.app.** {
    <fields>;
    <methods>;
}

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService
-keep class com.android.vending.licensing.ILicensingService
-keep class android.support.v4.** { *; }
-keep class androidx.fragment.app.** { *;}
-keep class androidx.appcompat.app.** {*;}

#ShareSDK混淆
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
-keep class m.framework.**{*;}

##阿里加速开始
#-keep class com.taobao.{*;}
#-keep class com.alibaba.{*;}
##阿里加速结束

## ----------------------------------
##      OkHttp
## ----------------------------------
-keepattributes Signature
-keepattributes Annotation
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.* { *; }
-dontwarn okio.**
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class okio.** { *; }
-keep class sun.misc.Unsafe { *; }

#Testin sdk开始
-dontwarn com.qamaster.android.**
-keep class com.qamaster.android.** {*;}
#Testin sdk结束

#可播放视频Webview开始
-keepclassmembers class name.cpr.VideoEnabledWebView$JavascriptInterface { public *; }
#可播放视频Webview结束

## ----------------------------------
##      Glide
## ----------------------------------
-keep class com.bumptech.glide.Glide { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep class com.bumptech.glide.request.RequestOptions {*;}
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-dontwarn com.bumptech.glide.**


## ----------------------------------
##      Picasso相关
## ----------------------------------
-keep class com.squareup.picasso.Picasso { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.picasso.**



#http://tools.android.com/tech-docs/support-annotations
-dontskipnonpubliclibraryclassmembers
-printconfiguration

-keep,allowobfuscation @interface  com.github.kr.co.namee.permissiongen.PermissionFoundDenied

-keep @com.github.kr.co.namee.permissiongen.PermissionFoundDenied class *
-keepclassmembers class * {
    @com.github.kr.co.namee.permissiongen.PermissionFoundDenied *;
}

-keep,allowobfuscation @interface  com.github.kr.co.namee.permissiongen.PermissionShowRational

-keep @com.github.kr.co.namee.permissiongen.PermissionShowRational class *
-keepclassmembers class * {
    @com.github.kr.co.namee.permissiongen.PermissionShowRational *;
}
#**********************************************方法2******************************************
#不启用shrink压缩优化，否则运用到注解声明的方法，由于没有被显示调用，而被移除掉，导致功能失效
#-dontshrink

#权限管理的注解类,避免混淆结束

#httpcore,httmime
-dontwarn org.apache.http.**
-keep class org.apache.http.** {*;}
#gson
-dontwarn com.google.gson.**
-keep class com.google.gson.** {*;}
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#东莞证券不混淆end
#-------bugly开始-------------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#-------bugly结束-------------
#-------arouter开始-------------
-dontwarn com.alibaba.android.arouter.**
-keep public class com.alibaba.android.arouter.**{*;}
-keep class * extends com.alibaba.android.arouter.facade.template.IProvider{*;}
-keep class com.alibaba.android.arouter.facade.annotation.Autowired
-keep class * {
    @com.alibaba.android.arouter.facade.annotation.Autowired <fields>;
}
-keepclasseswithmembers class * {
    @com.alibaba.android.arouter.facade.annotation.Autowired <methods>;
}
#-------arouter结束-------------

#ButterKnife开始
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#ButterKnife开始

-keep class **.R$* {
    <fields>;
}


## ----------------------------------
##      help us to debug
## ----------------------------------
-renamesourcefileattribute SourceFile
-keepattributes Exceptions
-keepattributes SourceFile,LineNumberTable,keepattributes
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes Signature
-keepattributes *Annotation*
-dontshrink

## ----------------------------------
##      Config need by TinkerPatch
## ----------------------------------
-keep class com.tinkerpatch.sdk.TinkerPatch { *; }
-keep class com.tinkerpatch.sdk.BuildConfig { *; }

-keep class com.tinkerpatch.sdk.TinkerPatch$Builder { *; }
-keep class com.tinkerpatch.sdk.server.RequestLoader { *; }
-keep class com.tinkerpatch.sdk.util.ContentLengthInputStream { *; }
-keep interface com.tinkerpatch.sdk.server.model.DataFetcher { *; }
-keep interface com.tinkerpatch.sdk.server.model.DataFetcher$DataCallback { *; }
-keep class com.tinkerpatch.sdk.server.model.TinkerClientUrl { *; }
-keep class com.tinkerpatch.sdk.server.callback.** { *; }
-keep class com.tinkerpatch.sdk.tinker.callback.** { *; }
-keep public class * extends android.app.Application
-keep class com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike { *; }
-keep class com.tencent.tinker.** { *; }

## ----------------------------------
##      Config from tinker
## ----------------------------------
-dontwarn com.tencent.tinker.anno.AnnotationProcessor
-keep @com.tencent.tinker.anno.DefaultLifeCycle public class *
-keep public class * extends android.app.Application {
    *;
}

-keep public class com.tencent.tinker.loader.app.ApplicationLifeCycle {
    *;
}
-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    *;
}

-keep public class com.tencent.tinker.loader.TinkerLoader {
    *;
}
-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    *;
}
-keep public class com.tencent.tinker.loader.TinkerTestDexLoad {
    *;
}
-keep public class com.tencent.tinker.loader.TinkerTestAndroidNClassLoader {
    *;
}

## ----------------------------------
##      your dex.loader patterns here
## ----------------------------------
#已经移交到normal和newhand那里去了
#-keep class com.junte.base.TDApplication
#-keep class com.tencent.tinker.loader.**


#ali httpdns start
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
#ali httpdns end
# Prism
-keep class com.tencent.wstt.** {*;}
-keep class com.kunpeng.pit.** {*;}
# End Prism



