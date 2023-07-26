#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <jni.h>

JNIEXPORT void JNICALL Java_SystemCallExample_printCurrentTime(JNIEnv *env, jobject obj) {
    // Make a system call to get the current time
    system("date");
}

// gcc -shared -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -m64 SystemCallExample.c -o systemcallexample.dll