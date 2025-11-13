#!/bin/bash

cd /opt
wget https://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
tar -xzf sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
mv sqoop-1.4.7.bin__hadoop-2.6.0 sqoop
rm sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz

export SQOOP_HOME=/opt/sqoop
export PATH=$PATH:$SQOOP_HOME/bin

cd $SQOOP_HOME/lib
wget https://jdbc.postgresql.org/download/postgresql-42.7.3.jar
wget https://repo1.maven.org/maven2/commons-lang/commons-lang/2.6/commons-lang-2.6.jar

echo "Sqoop installed!"
sqoop version
