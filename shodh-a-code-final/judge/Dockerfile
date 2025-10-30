
FROM openjdk:17-jdk-slim
WORKDIR /opt/judge
RUN useradd -m judgeuser
USER judgeuser
COPY entrypoint.sh /opt/judge/entrypoint.sh
RUN chmod +x /opt/judge/entrypoint.sh
ENTRYPOINT ["/opt/judge/entrypoint.sh"]
