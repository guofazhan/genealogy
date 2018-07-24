SERVER_NAME=${script.start.jar}
JAVA_HOME="/usr/java/jdk1.8.0_144/bin"
JAVA_OPTS="-server"
#memory
JAVA_OPTS="$JAVA_OPTS -Xmx4g -Xms4g -Xss256k -XX:MaxDirectMemorySize=1G"
#jmx
JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=60001 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
#gc
JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1ReservePercent=25 -XX:InitiatingHeapOccupancyPercent=40 -XX:+PrintGCDateStamps -Xloggc:$LOG_DIR/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_DIR/java.hprof -XX:+DisableExplicitGC -XX:-OmitStackTraceInFastThrow -XX:+PrintCommandLineFlags -XX:+UnlockCommercialFeatures -XX:+FlightRecorder"
#param
JAVA_OPTS="$JAVA_OPTS -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.util.Arrays.useLegacyMergeSort=true -Dfile.encoding=UTF-8"