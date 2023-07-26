public class SystemCallExample {
    static {
        System.load("D:\\college\\8th sem\\os\\os_impl\\test_jni\\systemcallexample.dll"); // Replace with the actual path to the shared library
    }

    private native void printCurrentTime();

    public static void main(String[] args) {
        SystemCallExample example = new SystemCallExample();
        example.printCurrentTime();
    }
}
