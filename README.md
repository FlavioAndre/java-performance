# java-performance

 docker run -v java-performance\src\:/home/dev/ -it openjdk bash
 
  javac com/teste/ValidaCPFv1.java
  java -XX:+PrintHeapAtGC -Xloggc:gc.log com.teste.ValidaCPFv1
  
