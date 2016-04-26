package com.shuyinqi.guava.file;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jiayusun on 2016/4/26.
 * Java的基本API对文件的操作很繁琐，为了向文件中写入一行文本，都需要写十几行的代码。guava对此作了很多改进，提供了很多方便的操作
 */
public class GuavaFile {
    //文件中写入字节流
    public static void testFileWrite(final String fileName,final String contents){
        Preconditions.checkNotNull(fileName,"Provided file name for writing must NOT be null.");
        Preconditions.checkNotNull(contents,"");
        final File newFile = new File(fileName);
        try {
            Files.write(contents.getBytes(),newFile);
        } catch (IOException e) {
            System.err.println( "ERROR trying to write to file '" + fileName + "' - " + e.toString());
        }
    }
    //获取文件内容
    public static  void testReadLines() throws IOException {
        String testFilePath = "d:\\\\test.txt";
        File testFile = new File(testFilePath);
        List<String> lines = Files.readLines(testFile, Charsets.UTF_8);
        for(String line:lines){
            System.out.println(line);
        }
    }
    //从一个大文件中逐行读取文本，并做行号计数
    public static void testLineProcessorReadLines() throws IOException {
        String testFilePath = "d:\\test.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        Files.readLines(testFile, Charsets.UTF_16, counter);
        System.out.println(counter.getResult());
    }
    //Guava中移动文件使用move方法，用法和copy一样
    public void testSimpleFileCopy(final String sourceFileName,final String targetFileName){
        Preconditions.checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        Preconditions.checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //演示 Files.equal(File,File) 来比较两个文件的内容
    public void demoEqual(final String fileName1, final String fileName2){
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);

        try {
            Files.equal(file1,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * touch方法创建或者更新文件的时间戳
     * createTempDir()方法创建临时目录
     * Files.createParentDirs(File) 创建父级目录
     * getChecksum(File)获得文件的checksum
     * hash(File)获得文件的hash
     * map系列方法获得文件的内存映射
     * getFileExtension(String)获得文件的扩展名
     * getNameWithoutExtension(String file)获得不带扩展名的文件名
     */

    public static void main(String[] args) {

    }

    //这个readLines的重载，需要我们实现一个LineProcessor的泛型接口，在这个接口的实现方法processLine方法中我们可以对行文本进行处理，getResult方法可以获得一个最终的处理结果，这里我们只是简单的返回了一个行计数
    static class CounterLine implements LineProcessor{
        private int rowNum=0;
        @Override
        public boolean processLine(String s) throws IOException {
            rowNum++;
            return true;
        }
        @Override
        public Object getResult() {
            return rowNum;
        }
    }
}
