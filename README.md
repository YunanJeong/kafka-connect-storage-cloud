# Kafka Connect Connector for S3
- S3 Sink Connector를 수정하기 위해 소스코드를 fork 해온 repository다.

# How to build
- 필요한 부분을 수정한 후 Maven 기반으로 다음과 같이 빌드한다.
    ```
    # maven 설치
    sudo apt install mvn
    
    # 빌드
    mvn install -f pom.xml -Dcheckstyle.skip -DskipTests
    ```
    - `-DskipTests`: 테스트 스킵. awscli로 AWS S3에 실제 접근하여 테스트가 수행되며 굉장히 오래걸리기도 하기 때문에 스킵해줌.
    - `-Dcheckstyle.skip`: 스타일체크 스킵. 이거 없이도 빌드는 가능하다. 가끔 이것 때문에 빌드 취소됨.
## 빌드관련 참고
- Confluent Hub에 배포되는 형태와 동일하게 하나의 S3 Sink Connector로서 전체가 빌드되도록 설정되어 있다.
- 소소한 기능을 추가해도 하나의 Connector로서 배포된다. 번거로운 대신 Dependency 버전 호환성은 잘 보장된다.
- [Partitioner 추가](https://github.com/YunanJeong/kafka-connect-s3-without-topicname)와 같은 것들은 별도 jar로 배포가능하다.
    - 이 때 원본 S3 Conenctor의 Dependency는 포함시킬 필요없다.
    - 어차피 Kafka Connect는 plugin-path에서 원본 S3 Connector와 추가 배포판을 모두 읽어서 Dependency공유가 가능하기 때문

# 현재 수정한 부분
- 파티셔너 추가: TimeBasedPartitioner를 Override하여 수정. S3 업로드시 상위 파티션으로 topic이름이 강제할당되는 것을 삭제
```
/kafka-connect-s3/src/main/java/io.confluent.connect.s3/TopiclessTimeBasedPartitioner
```



---
# Original Readme
# Kafka Connect Connector for S3
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bhttps%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-storage-cloud.svg?type=shield)](https://app.fossa.io/projects/git%2Bhttps%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-storage-cloud?ref=badge_shield)


*kafka-connect-storage-cloud* is the repository for Confluent's [Kafka Connectors](http://kafka.apache.org/documentation.html#connect)
designed to be used to copy data from Kafka into Amazon S3. 

## Kafka Connect Sink Connector for Amazon Simple Storage Service (S3)

Documentation for this connector can be found [here](http://docs.confluent.io/current/connect/connect-storage-cloud/kafka-connect-s3/docs/index.html).

Blogpost for this connector can be found [here](https://www.confluent.io/blog/apache-kafka-to-amazon-s3-exactly-once).

# Development

To build a development version you'll need a recent version of Kafka 
as well as a set of upstream Confluent projects, which you'll have to build from their appropriate snapshot branch.
See [the kafka-connect-storage-common FAQ](https://github.com/confluentinc/kafka-connect-storage-common/wiki/FAQ)
for guidance on this process.

You can build *kafka-connect-storage-cloud* with Maven using the standard lifecycle phases.

# Running Integration Tests
Integration tests are run as part of `mvn install`; however one needs to first configure the environment variable`AWS_CREDENTIALS_PATH` to point to a json file path with following structure:
```
{
    "aws_access_key_id": "<key>",
    "aws_secret_access_key": "<secret>"
}
```

# Contribute

- Source Code: https://github.com/confluentinc/kafka-connect-storage-cloud
- Issue Tracker: https://github.com/confluentinc/kafka-connect-storage-cloud/issues
- Learn how to work with the connector's source code by reading our [Development and Contribution guidelines](CONTRIBUTING.md).


# License

This project is licensed under the [Confluent Community License](LICENSE).


[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bhttps%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-storage-cloud.svg?type=large)](https://app.fossa.io/projects/git%2Bhttps%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-storage-cloud?ref=badge_large)
