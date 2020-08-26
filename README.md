# java-performance

## Exemplo de subir java no docker interativo no bash
docker run -v java-performance\src\:/home/dev/ -it openjdk bash
 
 ## Compilar 
javac com/teste/ValidaCPFv1.java
  
## Executar com log do GC Java 8
java –XX:+UseConcMarkSweepGC -XX:+PrintGC  -XX:+PrintHeapAtGC  -XX:+PrintGCDetails -Xloggc:log/gc1.log com.teste.ValidaCPFv1


## Executar com log do GC Java 14
java -Xlog:all:log/gc14-1.log com.teste.ValidaCPFv1
