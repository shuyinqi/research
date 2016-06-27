package com.shuyinqi.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by jiayusun on 2016/5/26.
 */
public class BasicChannel {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int byteRead = inChannel.read(buf);
        while (byteRead != -1) {
            System.out.println("Read" + byteRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print(buf.get());
            }
            buf.clear();
            byteRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
