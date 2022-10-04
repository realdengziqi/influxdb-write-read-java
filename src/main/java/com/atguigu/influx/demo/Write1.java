package com.atguigu.influx.demo;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.internal.AbstractWriteClient;
import com.influxdb.client.internal.MeasurementMapper;
import com.influxdb.client.write.Point;

import java.time.Instant;

/**
 * @author: tony
 * @date: 2022/9/23 3:23
 * @description: 这里是使用同步方式向InfluxDB写入数据的示例代码
 *
 */
public class Write1 {

    /** tony 操作时需要换成自己的 **/
    private static char[] token = "B9buVw6CYGRgLSATNhK6-k_8XL7I8r_CaGJ-PJqQjJw22h72tiCcONvjOWODXB9vqRrMhSbu5K_6dckWq13WRw==".toCharArray();

    /** 组织名称 操作时需要换成自己的 **/
    private static String org = "atguigu";

    /** 存储桶名称 **/
    private static String bucket = "example_java";

    /** InfluxDB服务的url **/
    private static String url = "http://host1:8086/";

    public static void main(String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org, bucket);
        WriteApiBlocking writeApiBlocking = influxDBClient.getWriteApiBlocking();

        // 0. 使用InflxuDB行协议写入
//        writeApiBlocking.writeRecord(WritePrecision.MS,"temperature,location=north value=50");

        // 1. 使用Point写入
//        Point point = Point.measurement("temperature")
//                .addTag("location", "west")
//                .addField("value", 38.0)
//                .time(Instant.now(),WritePrecision.NS)
//                ;
//        writeApiBlocking.writePoint(point);

        // 2. 使用POJO类写入
        DemoPOJO demoPOJO = new DemoPOJO("east", 22.2, Instant.now());
        writeApiBlocking.writeMeasurement(WritePrecision.MS,demoPOJO);

        // 3. 调用close方法会关闭并释放一些比如守护线程之类的对象。
        influxDBClient.close();
    }
}
