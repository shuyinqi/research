package com.shuyinqi.bloomFilter;

import java.util.BitSet;

/**
 * 布隆过滤器是对哈希算法的一种创新，而且需要消耗的空间也很小，错误率很低。总之这种创新的思路很值得学习，是一种对bit这种数据类型的运用
 *
 * 引用吴军博士的《数学之美》中所言，哈希表的空间效率还是不够高。
 * 如果用哈希表存储一亿个垃圾邮件地址，每个email地址 对应 8bytes, 而哈希表的存储效率一般只有50%，
 * 因此一个email地址需要占用16bytes. 因此一亿个email地址占用1.6GB，如果存储几十亿个email address则需要上百GB的内存。
 * 除非是超级计算机，一般的服务器是无法存储的。
 *
 *当一个元素被加入集合时，通过 K 个 Hash 函数将这个元素映射成一个位阵列（Bit array）中的 K 个点，把它们置为 1。检索时，我们只要看看这些点是不是都是 1 就（大约）知道集合中有没有它了
 *如果这些点有任何一个 0，则被检索元素一定不在；如果都是 1，则被检索元素很可能在。
 *
 * 优点
 * 空间效率和查询时间都远远超过一般的算法，布隆过滤器存储空间和插入 / 查询时间都是常数O(k)。另外, 散列函数相互之间没有关系，方便由硬件并行实现。布隆过滤器不需要存储元素本身，在某些对保密要求非常严格的场合有优势
 *缺点
 * 误算率是其中之一。随着存入的元素数量增加，误算率随之增加。但是如果元素数量太少，则使用散列表足矣
 *一般情况下不能从布隆过滤器中删除元素. 我们很容易想到把位数组变成整数数组，每插入一个元素相应的计数器加 1, 这样删除元素时将计数器减掉就可以了。然而要保证安全地删除元素并非如此简单。首先我们必须保证删除的元素的确在布隆过滤器里面. 这一点单凭这个过滤器是无法保证的。另外计数器回绕也会造成问题
 *
 * 具体概率推导
 * http://www.cnblogs.com/haippy/archive/2012/07/13/2590351.html
 *
 * Created by jiayusun on 2016/6/1.
 */
public class BloomFilter {

    private static final int DEFAULT_SIZE=2<<24;//布隆过滤器的比特长度
    private static final int[] seeds={3,5,7,11,13,31,37,61};//选取质数，能降低错误率
    private static BitSet bits = new BitSet(DEFAULT_SIZE);
    private static SimpleHash[] func = new SimpleHash[seeds.length];

    public static void addValue(String value){
        for(SimpleHash f: func){
            bits.set(f.hash(value),true);
        }
    }
    public static void add(String value){
        if( value != null ){
            addValue(value);
        }
    }
    public static boolean contains(String value){
        if(value==null){
            return false;
        }
        boolean ret = true;
        for(SimpleHash f: func){
            ret = ret &&bits.get(f.hash(value));
        }
        return ret;
    }

    public static void main(String[] args) {
        String value = "xkeyideal@gmail.com";
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
        add(value);
        System.out.println(contains(value));
        System.out.println(contains("xkeyideal@mail.com"));
    }
}

class SimpleHash{//这玩意相当于C++中的结构体
    private int cap;
    private int seed;
    public SimpleHash(int cap,int seed){
        this.cap=cap;
        this.seed=seed;
    }
    public int hash(String value){ //字符串hash,选取很好的hash函数很重要
        int result=0;
        int len = value.length();
        for(int i=0;i<len;i++){
            result=seed*result+value.charAt(i);
        }
        return (cap-1)&result;
    }
}
