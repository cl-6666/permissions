# 安卓编译时权限申请
### 版本介绍:  
v1.1.10  
1.采用aspectj技术
2.编译时申请权限不影响性能
3.一行代码调用--方便


### 使用介绍: 
```java
gradle配置：

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath 'org.aspectj:aspectjtools:1.9.2'
        classpath 'org.aspectj:aspectjweaver:1.9.2'
    }
}


import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main

final def log = project.logger
final def variants = project.android.applicationVariants

variants.all { variant ->
    if (!variant.buildType.isDebuggable()) {
        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
        return;
    }

    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        log.debug "ajc args: " + Arrays.toString(args)

        MessageHandler handler = new MessageHandler(true);
        new Main().run(args, handler);
        for (IMessage message : handler.getMessages(null, true)) {
            switch (message.getKind()) {
                case IMessage.ABORT:
                case IMessage.ERROR:
                case IMessage.FAIL:
                    log.error message.message, message.thrown
                    break;
                case IMessage.WARNING:
                    log.warn message.message, message.thrown
                    break;
                case IMessage.INFO:
                    log.info message.message, message.thrown
                    break;
                case IMessage.DEBUG:
                    log.debug message.message, message.thrown
                    break;
            }
        }
    }
}

//代码使用
 @Permission(value = Manifest.permission.READ_EXTERNAL_STORAGE, requestCode = 200)
    public void testRequest() {
        Toast.makeText(this, "权限申请成功...", Toast.LENGTH_SHORT).show();
    }

    @PermissionCancel
    public void testCancel() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    public void testDenied() {
        Toast.makeText(this, "权限被拒绝（用户勾选了，不再提醒）", Toast.LENGTH_SHORT).show();
    }

``` 


