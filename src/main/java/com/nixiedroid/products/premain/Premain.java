package com.nixiedroid.products.premain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class Premain implements ClassFileTransformer {
    static final String pkgName = Premain.class.getPackageName();
    static final String classPrefix = (pkgName.contains(".") ?
            pkgName.substring(pkgName.lastIndexOf(".")):
            pkgName).replace(".", "/");


    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new Premain());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new Premain());
    }

    @Override
    public byte[] transform(Module module, ClassLoader loader,
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
       // if (className.startsWith(classPrefix)) {
            System.out.println("Loaded [" + className + "] from module: [" + module.getName() +" ]" );
       // }
        return null;
    }
}
