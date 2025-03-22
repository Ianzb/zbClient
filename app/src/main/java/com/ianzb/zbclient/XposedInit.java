package com.ianzb.zbclient;

import com.ianzb.zbclient.hook.ToastHook;
import com.ianzb.zbclient.hook.zbHook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedInit implements IXposedHookLoadPackage {
    private static final Map<String, List<zbHook>> PACKAGE_HOOKS = new HashMap<String, List<zbHook>>() {{
        put("com.android.systemui", Arrays.asList(new ToastHook()));
    }};

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // 获取当前包名的Hook配置
        List<zbHook> hooks = PACKAGE_HOOKS.get(lpparam.packageName);
        if (hooks == null) return;

        // 执行所有注册的Hook
        for (zbHook hook : hooks) {
            try {
                hook.hook(lpparam.classLoader);
                XposedBridge.log("Hook执行成功！[" + hook.getClass().getSimpleName() + "]");
            } catch (Throwable e) {
                XposedBridge.log("Hook执行失败 [" + hook.getClass().getSimpleName() + "]: " + e);
            }
        }
    }
}
