package com.ianzb.zbclient.hook;


import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class ToastHook implements zbHook {
    @Override
    public void hook(ClassLoader classLoader) {
        XposedHelpers.findAndHookMethod(
                "android.widget.Toast",
                classLoader,
                "makeText",
                Context.class,
                CharSequence.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.args[1] = "Hooked by LSPosed！\n原始内容：" + param.args[1];
                        XposedBridge.log("Toast内容已修改: " + param.args[1]);
                    }
                });

        // Hook show方法
        XposedHelpers.findAndHookMethod(
                "android.widget.Toast",
                classLoader,
                "show",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        // 保持Toast强制显示
                    }
                });
    }
}

