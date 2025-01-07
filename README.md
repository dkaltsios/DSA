# DSA Assignment: ADT Design and Implementation
Authors:
- George Chatzopoulos
- Dimitris Kaltsios
- Sudak Yaroslav
- Aris Zananiri
- Alexandros Drakoulis
## Installation
### Requirements
- Git : [install](https://git-scm.com/downloads)
- Java : [install](https://www.java.com/en/download/help/download_options.html)
- Maven : [install](https://maven.apache.org/download.cgi)

Alternatively, Maven can be installed on `Windows` with:
- Scoop

Install Scoop
```bash
iwr -useb get.scoop.sh | iex
```
Install Maven
```bash
scoop install maven
```
- Chocolatey

[Install Chocolatey](https://docs.chocolatey.org/en-us/choco/setup/)
```bash
choco install maven
```
And for `Red Hat` based distributions with:
```bash
sudo dnf install maven
```
Note
```
If the alternative methods are chosen, the JAVA_HOME environment variable may still needed to be set. 

More information for Windows users here: 
https://phoenixnap.com/kb/install-maven-windows

More information for Linux users here: 
https://www.digitalocean.com/community/tutorials/install-maven-linux-ubuntu
```

Verify maven installation
```bash
mvn --version
```

### Setup
Clone the repository
```bash
git clone https://github.com/dkaltsios/DSA.git
```
Alternatively, you can use the other methods provided by GitHub (SSH or GitHub CLI)

Run the project
```bash
mvn exec:java
```