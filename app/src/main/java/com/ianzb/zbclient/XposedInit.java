package com.ianzb.zbclient;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedInit implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // 目标应用包名（这里以系统UI为例，可替换为任意应用包名）
        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }

        try {
            // Hook Toast的makeText方法
            XposedHelpers.findAndHookMethod(
                    "android.widget.Toast", // 目标类
                    lpparam.classLoader,
                    "makeText", // 方法名
                    android.content.Context.class, // 参数1：Context
                    CharSequence.class,            // 参数2：原始文本
                    int.class,                     // 参数3：显示时长
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            // 修改Toast内容（将原始文本替换为自定义内容）
                            param.args[1] = "Hooked by LSPosed！\n原始内容：" + param.args[1];

                            // 打印日志（可选）
                            XposedBridge.log("成功Hook Toast: " + param.args[1]);
                        }
                    });

            // 可选：同时Hook show()方法防止被取消
            XposedHelpers.findAndHookMethod(
                    "android.widget.Toast",
                    lpparam.classLoader,
                    "show",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            // 强制显示Toast
                        }
                    });

        } catch (Throwable e) {
            XposedBridge.log("Hook失败: " + e);
        }
    }
}