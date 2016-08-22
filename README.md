# Jgui-creator

# How to use

# How to develope

## Code Formatter

Import this [Codestyle](https://github.com/WI13C/Jgui-creator/blob/master/orga/eclipse-java-codestyle.xml) into your eclipse.



##Import the project into eclipse
Eclipse -> Import -> Existing Maven Projects -> Code Ordner auswählen und alle pom.xml auswählen.

##Install maven
1. Download [this](https://maven.apache.org/download.cgi)
2. Install with [this](https://maven.apache.org/install.html) tutorial
3. Learn how tu use with [this](https://maven.apache.org/run.html) tutorial.

##Build the project
1. Open the terminal in the code-folder
2. run:
```bash
mvn clean install
```
3. if the build is successfully, the created jar-file can be found at 'framework/target/'


### Git Commit Messages

**DO** Write the summary line and description of what you have done in the imperative mode, that is as if you were commanding someone. Start the line with "Fix", "Add", "Change" instead of "Fixed", "Added", "Changed". Always leave the second line blank. Line break the commit message (to make the commit message readable without having to scroll horizontally in gitk).

**DON'T** Don't end the summary line with a period - it's a title and titles don't end with a period.

**Tips** If it seems difficult to summarize what your commit does, it may be because it includes several logical changes or bug fixes, and are better split up into several commits using git add -p.
