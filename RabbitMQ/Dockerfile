# 기본 베이스 이미지
FROM openjdk:23-jdk

# 빌드할 때 전달받을 JAR 파일 위치
ARG JAR_FILE=build/libs/*.jar

# JAR 파일을 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# 8080 포트 노출
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]