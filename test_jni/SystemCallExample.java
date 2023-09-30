public class SystemCallExample {
    static {
        System.load("/home/aashish/java_projects/os/test_jni/libsystemcallexample.so"); // Replace with the actual path to the shared library
    }

    private native void printCurrentTime();

    public static void main(String[] args) {
        SystemCallExample example = new SystemCallExample();
        example.printCurrentTime();
    }
}
