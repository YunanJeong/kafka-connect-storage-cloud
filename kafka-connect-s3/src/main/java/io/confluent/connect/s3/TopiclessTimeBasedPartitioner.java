// package io.github.yunanjeong.custom;
package io.confluent.connect.s3;
import io.confluent.connect.storage.partitioner.TimeBasedPartitioner;

public class TopiclessTimeBasedPartitioner<T> extends TimeBasedPartitioner<T> {
    @Override
    public String generatePartitionedPath(String topic, String encodedPartition) {
        return encodedPartition;
    }
}
