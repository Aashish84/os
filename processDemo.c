#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t child_pid;

    child_pid = fork();

    if (child_pid == -1) {
        // Error handling
        perror("fork");
        return 1;
    }

    if (child_pid == 0) {
        // This code is executed in the child process
        printf("Child process (PID=%d)\n", getpid());
    } else {
        // This code is executed in the parent process
        printf("Parent process (PID=%d), Child PID=%d\n", getpid(), child_pid);
    }

    return 0;
}
