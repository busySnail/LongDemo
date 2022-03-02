#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_cn_com_native_1lib_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello malong i'm from C++";
    return env->NewStringUTF(hello.c_str());
}

//从native调用Java 成员method
extern "C" JNIEXPORT jstring JNICALL
Java_cn_com_native_1lib_NativeLib_testCallMethod(
        JNIEnv *env,
        jobject instance) {

    jclass a_class = env->GetObjectClass(instance);
    jmethodID a_method = env->GetMethodID(a_class, "describe", "()Ljava/lang/String;");
    jstring pring = (jstring) (env)->CallObjectMethod(instance, a_method);
    char *print = (char *) (env)->GetStringUTFChars(pring, 0);
    return env->NewStringUTF(print);
}

//从native调用Java 静态method
extern "C" JNIEXPORT jstring JNICALL
Java_cn_com_native_1lib_NativeLib_testStaticCallMethod(
        JNIEnv *env,
        jclass type) {

    jmethodID a_method = env->GetStaticMethodID(type, "staticDescribe", "()Ljava/lang/String;");
    jstring pring = (jstring) (env)->CallStaticObjectMethod(type, a_method);
    char *print = (char *) (env)->GetStringUTFChars(pring, 0);
    return env->NewStringUTF(print);
}

JavaVM *g_VM;

extern "C" JNIEXPORT void JNICALL
Java_cn_com_native_1lib_NativeLib_startDownload(
        JNIEnv *env,
        jobject instance, jobject jcallback) {

    jclass a_class = env->GetObjectClass(jcallback);
    jmethodID a_method = env->GetMethodID(a_class, "onFinish", "(Ljava/lang/String;)V");

    // jobject jobj = env->AllocObject(a_class);

    std::string msg = "callback msg from native";
    (env)->CallVoidMethod(jcallback, a_method, env->NewStringUTF(msg.c_str()));

}

