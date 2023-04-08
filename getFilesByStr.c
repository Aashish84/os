#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdbool.h>
#include <Windows.h>

int main() {
    char path[MAX_PATH];
    DWORD path_len = GetCurrentDirectory(MAX_PATH, path); // Get the current path
    if (path_len == 0) {
        printf("Error getting path.\n");
        return -1;
    }

    printf("enter file name : ");
    char userFilename[100];
    scanf("%s",userFilename);  

    DIR *dir;
    struct dirent *ent;
    char *dir_name = path; //directory path
    int fileCount = 0;
    if ((dir = opendir(dir_name)) != NULL) {
        // iterate through all files in the directory
        bool flag = false; 
        while ((ent = readdir(dir)) != NULL) {
            // check if the file name starts with given string
            if (strncasecmp(ent->d_name, userFilename, strlen(userFilename)) == 0) {
                printf("%s\n", ent->d_name);
                flag=true;
                fileCount++;
            }
        }
        closedir(dir);
    } else {
        printf("Could not open directory\n");
        return 1;
    }
    printf("\n***********************\n");
    printf (" *** %i file found ***\n",fileCount);
    printf("***********************\n");
    return 0;
}

// =========================================================================
// =========================================================================
/*
    char path[MAX_PATH];
    DWORD path_len = GetCurrentDirectory(MAX_PATH, path);
    is a statement in C that uses the GetCurrentDirectory() function to get the current working directory (i.e., the current path) and store it in the path variable.

    Here's what's happening in this statement:

    char path[MAX_PATH]; declares a character array called path that can hold up to MAX_PATH characters.

    MAX_PATH is a constant defined in the Windows.h header file that specifies the maximum length of a path on Windows.

    DWORD is a type defined in Windows.h that represents an unsigned 32-bit integer.

    path_len is a variable of type DWORD that is used to store the length of the current working directory returned by GetCurrentDirectory().

    GetCurrentDirectory() is a function from the Windows.h header file that gets the current working directory and stores it in a buffer.
    The function takes two arguments:
        MAX_PATH is a constant that specifies the maximum length of a path on Windows.
        path is a buffer (i.e., an array of characters) that will hold the current working directory.

    The function returns the length of the current working directory in characters (excluding the null terminator).

    GetCurrentDirectory() modifies the path buffer to contain the current working directory.

    So, in summary, char path[MAX_PATH]; DWORD path_len = GetCurrentDirectory(MAX_PATH, path); declares a character array called path that can hold up to MAX_PATH characters, gets the current working directory using GetCurrentDirectory() and stores it in path, and also stores the length of the current working directory (in characters) in the path_len variable. 
*/
// =========================================================================
// =========================================================================
/*
    DIR *dir; is a statement in C that declares a pointer variable dir of type DIR.

    DIR is a type defined in the <dirent.h> header file and is used to represent a directory stream. It is used in conjunction with the functions opendir(), readdir(), and closedir() to read the contents of a directory.

    When opendir() is called to open a directory, it returns a pointer to a DIR type, which can be stored in a variable of type DIR *. This pointer is then used in subsequent calls to readdir() and closedir().
*/
// =========================================================================
// =========================================================================
/*
    strncasecmp(ent->d_name, str, 1) == 0

    In C, strncasecmp() is a function that compares two strings up to a specified number of characters, ignoring the case of the characters. Here's what the expression strncasecmp(ent->d_name, str, 1) == 0 means:

    ent->d_name is a string that represents the name of a file or directory in a directory stream. The -> operator is used to access the d_name member of the dirent structure pointed to by the ent pointer.

    str is a string that represents the search term or pattern to match against the filenames in the directory stream.

    1 is the maximum number of characters to compare in the two strings.

    strncasecmp() compares the first 1 character of ent->d_name and str, ignoring the case of the characters.

    If the first character of ent->d_name matches the first character of str (ignoring case), then strncasecmp() returns 0.

    Therefore, the expression strncasecmp(ent->d_name, str, 1) == 0 evaluates to true if the first character of ent->d_name matches the first character of str (ignoring case), and false otherwise
*/