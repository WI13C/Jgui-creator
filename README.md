# Jgui-creator

![GUI-Screenshot](screenshot.png?raw=true "Screenshot")

# How to use

1. At your pom.xml
```xml
<dependency>
  <groupId>de.dhbw.wi13c.jguicreator</groupId>
  <artifactId>framework</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

2. At your parseable Object
```java
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;

public class Person {

	@NotNull
	@Size(min = 2)
	private String vorname;

	@PieChart
	private Map<String, ? extends Number> einkommensZusammensetzung;

	@BarChart
	private Map<String, ? extends Number> einkommensEntwicklung;
  .
  .
  .
```

3. To create the gui
```java
import de.dhbw.wi13c.jguicreator.DomainObjectParser;
import de.dhbw.wi13c.jguicreator.impl.GuiCreator;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class Showcase {

	public static void main(String[] args) {

		Person aPerson = new Person("Chuck", "Norris", ...);

		GuiCreator.createView(aPerson, new SavedListener<Person>() {
			public void saved(Person object) {
        System.out.println('Changed data got saved');
				System.out.println(object.toString());
			}
		}, new DomainObjectParser());

	}
}
```


##Run the sample-project
1. install [java-jre](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
2. clone or [download](https://github.com/WI13C/Jgui-creator/archive/master.zip) this project
3. navigate with your terminal into the project-folder
4. run this command:
```
java -jar Showcase.jar
```

# How to develope

## Code Formatter

Import this [Codestyle](https://github.com/WI13C/Jgui-creator/blob/master/orga/eclipse-java-codestyle.xml) into your eclipse.



##Import the project into eclipse
1. Eclipse -> Import -> Existing Maven Projects -> Code
2. select the folder and select all pom.xml-files

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
