# 初识IO
> IO，即in和out，也就是输入和输出，指应用程序和外部设备之间的数据传递，常见的外部设备包括文件、管道、网络连接。

Java 中是通过流处理IO 的，那么什么是流？

> 流（Stream），是一个抽象的概念，是指一连串的数据（字符或字节），是以先进先出的方式发送信息的通道。

当程序需要读取数据的时候，就会开启一个通向数据源的流，这个数据源可以是文件，内存，或是网络连接。类似的，当程序需要写入数据的时候，就会开启一个通向目的地的流。这时候你就可以想象数据好像在这其中“流”动一样。

## 流的特点
1. 先进先出：最先写入输出流的数据最先被输入流读取到。
2. 顺序存取：可以一个接一个的往流中写入一串字节，读出时也将按写入顺序读取一串字节，不能随机访问中间的数据。（`RandomAccessFile`除外）。
3. 只读或只写：每个流只能是输入流或输出流的一种，不能同时具备两个功能，输入流只能进行读操作，对输出流只能进行写操作。在一个数据传输通道中，如果既要写入数据，又要读取数据，则要分别提供两个流。

## IO流分类

IO流主要的分类方式有以下3种：

1. 按数据流的方向：输入流、输出流
2. 按处理数据单位：字节流、字符流
3. 按功能：节点流、处理流

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-1.png)

### 输入流与输出流

输入与输出是相对于应用程序而言的，比如文件读写，读取文件是输入流，写文件是输出流。

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-2.png)

### 字节流与字符流

字节流与字符流的用法几乎完全一样，区别在于字节流和字符流所操作的数据单元不同，字节流操作的单元数据是8位的字节，字符流操作的是数据单元为16位字节的字符。

**为什么要有字符流？**

Java中字符是采用Unicode标准，Unicode编码中，一个英文为一个字节，一个中文为两个字节。

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-3.png)

而在UTF-8编码中，一个中文字符是3个字节。例如下图中，“云深不知处”5个中文字对应的是15个字节：-28-70-111-26-73-79-28-72-115-25-97-91-27-92-124

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-4.png)

如果使用字节流处理中文，如果一次读写一个字符对应的直接数就不会有问题，一旦讲一个字符对应的字节分裂开来，就会出现乱码。为了更方便的处理这些中文字符，Java就推出了字符流。

**字节流和字符流的其他区别：**

1. 字节流一般用来处理图像、视频、音频、PPT、Word等类型的文件。字符流一般用于处理纯文本类型的文件，如TXT文件等，但不能处理图像视频等非文本文件。字节流可以处理一切文件，而字符流只能处理纯文本文件。
2. 字节流本身没有缓冲区，缓冲字节流相对于字节流，效率提升非常高。而字符流本身就带有缓冲区，缓冲字符流相对于字符流效率提升就没有那么大了。

以写文件为例，查看字符流的源码，利用到缓冲区：

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-5.png)

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-6.png)

### 节点流和处理流

**节点流**：直接操作数据读写的流类，比如`FileInputStream`

**处理流**：对一个已经存在的流的链接和封装，通过对数据进行处理，为程序提供功能强大、灵活的读写功能，例如`BufferedInputStream`（缓冲字节流）

处理流和节点流应用了Java的装饰者设计模式。

下图就很形象地描绘了节点流和处理流，处理流是对节点流的封装，最终的数据处理还是由节点流完成的。

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-7.png)

在诸多处理流中，有一个非常重要，那就是**缓冲流**。

程序与磁盘的交互相对于内存运算是很慢的，容易成为程序的性能瓶颈。减少程序与磁盘的交互，是提升程序效率一种有效手段。缓冲流，就应用这种思路：普通流每次读写一个字节，而缓冲流在内存中设置一个缓存区，缓冲区先存储足够的待操作数据后，再与内存或磁盘进行交互。这样，在总数据量不变的情况下，通过提高每次交互的数据量，减少了交互次数。

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-8.png)

完整的IO分类图如下：

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-9.png)

## 案例

详见代码：[IOTest](https://github.com/MrLsss/study/blob/main/study-java/src/main/java/com/study/java/io/IOTest.java)



# IO流对象

Java种提供了40多个类，我们只需要详细了解一下其中比较重要的就可以满足日常应用了。

## File类

> `File`类是用来操作文件的类，但它不能操作文件中的数据。

`public class File extends Object implements Serializable, Comparable<File>`

`File`类实现了`Serializable`、 `Comparable<File>`，说明它是支持序列化和排序的。

**File类的构造方法**

|              方法名               |                             说明                             |
| :-------------------------------: | :----------------------------------------------------------: |
|  File(File parent, String child)  | 根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。 |
|       File(String pathname)       | 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。 |
| File(String parent, String child) | 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。 |
|           File(URI uri)           | 通过将给定的 file: URI 转换为一个抽象路径名来创建一个新的 File 实例。 |

**File类的常用方法**

|       方法        |                             说明                             |
| :---------------: | :----------------------------------------------------------: |
|  createNewFile()  | 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。 |
|     delete()      |              删除此抽象路径名表示的文件或目录。              |
|     exists()      |          测试此抽象路径名表示的文件或目录是否存在。          |
| getAbsoluteFile() |              返回此抽象路径名的绝对路径名形式。              |
| getAbsolutePath() |             返回此抽象路径名的绝对路径名字符串。             |
|     length()      |             返回由此抽象路径名表示的文件的长度。             |
|      mkdir()      |                 创建此抽象路径名指定的目录。                 |
|     mkdirs()      | 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录。级联创建目录 |

**File类使用实例**

```java
public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Mu/fileTest.txt");

		// 判断文件是否存在
		if (!file.exists()) {
			// 不存在则创建
			file.createNewFile();
		}
		System.out.println("文件的绝对路径：" + file.getAbsolutePath());
		System.out.println("文件的大小：" + file.length());

		// 刪除文件
		file.delete();
	}
}
```

## 字节流

`InputStream`与`OutputStream`是两个抽象类，是字节流的基类，所有具体的字节流实现类都是分别继承了这两个类。

以`InputStream`为例，它继承了`Object`，实现了`Closeable`

`public abstract class InputStream extends Object implements Closeable`

`InputStream`类有很多的实现子类，下面列举了一些比较常用的：

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-10.png)

详细说明一下上图中的类：

- **InputStream**：InputStream是所有字节输入流的抽象基类，前面说过抽象类不能被实例化，实际上是作为模板而存在的，为所有实现类定义了处理输入流的方法。
- **FileInputSream**：文件输入流，一个非常重要的字节输入流，用于对文件进行读取操作。
- **PipedInputStream**：管道字节输入流，能实现多线程间的管道通信。
- **ByteArrayInputStream**：字节数组输入流，从字节数组(byte[])中进行以字节为单位的读取，也就是将资源文件都以字节的形式存入到该类中的字节数组中去。
- **FilterInputStream**：装饰者类，具体的装饰者继承该类，这些类都是处理类，作用是对节点类进行封装，实现一些特殊功能。
- **DataInputStream**：数据输入流，它是用来装饰其它输入流，作用是“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
- **BufferedInputStream**：缓冲流，对节点流进行装饰，内部会有一个缓存区，用来存放字节，每次都是将缓存区存满然后发送，而不是一个字节或两个字节这样发送，效率更高。
- **ObjectInputStream**：对象输入流，用来提供对基本数据或对象的持久存储。通俗点说，也就是能直接传输对象，通常应用在反序列化中。它也是一种处理流，构造器的入参是一个InputStream的实例对象。

`OutputStream`类继承关系图：

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-11.png)

`OutputStream`类继承关系与`InputStream`类似，需要注意的是`PrintStream`。

## 字符流

与字节流类似，字符流也有两个抽象基类，分别是`Reader`和`Writer`。其他的字符流实现类都是继承了这两个类。

以`Reader`为例，它的主要实现子类如下图：

![](https://cdn.jsdelivr.net/gh/mrlsss/images@main/Java/IO/IO学习总结-12.png)

各个类的详细说明：

- **InputStreamReader**：从字节流到字符流的桥梁（InputStreamReader构造器入参是FileInputStream的实例对象），它读取字节并使用指定的字符集将其解码为字符。它使用的字符集可以通过名称指定，也可以显式给定，或者可以接受平台的默认字符集。

- **BufferedReader**：从字符输入流中读取文本，设置一个缓冲区来提高效率。BufferedReader是对InputStreamReader的封装，前者构造器的入参就是后者的一个实例对象。

- **FileReader**：用于读取字符文件的便利类，new FileReader(File file)等同于new InputStreamReader(new FileInputStream(file, true),"UTF-8")，

- **FileReader**：不能指定字符编码和默认字节缓冲区大小。

- **PipedReader** ：管道字符输入流。实现多线程间的管道通信。

- **CharArrayReader**：从Char数组中读取数据的介质流。

- **StringReader** ：从String中读取数据的介质流。

Writer与Reader结构类似，方向相反，不再赘述。唯一有区别的是，Writer的子类PrintWriter。

# IO流方法

## 字节流方法

字节输入流`InputStream`主要方法：

- read() ：从此输入流中读取一个数据字节。

- read(byte[] b) ：从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。

- read(byte[] b, int off, int len) ：从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。

- close()：关闭此输入流并释放与该流关联的所有系统资源。

字节输出流`OutputStream`主要方法：

- write(byte[] b) ：将 b.length 个字节从指定 byte 数组写入此文件输出流中。
- write(byte[] b, int off, int len) ：将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
- write(int b) ：将指定字节写入此文件输出流。
- close() ：关闭此输入流并释放与该流关联的所有系统资源。

## 字符流方法

字符输入流`Reader`主要方法：

- read()：读取单个字符。

- read(char[] cbuf) ：将字符读入数组。

- read(char[] cbuf, int off, int len) ： 将字符读入数组的某一部分。

- read(CharBuffer target) ：试图将字符读入指定的字符缓冲区。

- flush() ：刷新该流的缓冲。

- close() ：关闭此流，但要先刷新它。

字符输出流`Writer`主要方法：

- write(char[] cbuf) ：写入字符数组。
- write(char[] cbuf, int off, int len) ：写入字符数组的某一部分。
- write(int c) ：写入单个字符。
- write(String str) ：写入字符串。
- write(String str, int off, int len) ：写入字符串的某一部分。
- flush() ：刷新该流的缓冲。
- close() ：关闭此流，但要先刷新它。

另外，字符缓冲流还有两个独特的方法：

- BufferedWriter类newLine() ：写入一个行分隔符。这个方法会自动适配所在系统的行分隔符。
- BufferedReader类readLine() ：读取一个文本行。

# 其他

## 位、字节、字符

字节(Byte)是计量单位，表示数据量多少，是计算机信息技术用于计量存储容量的一种计量单位，通常情况下一字节等于八位。

字符(Character)计算机中使用的字母、数字、字和符号，比如’A’、‘B’、’$’、’&'等。

一般在英文状态下一个字母或字符占用一个字节，一个汉字用两个字节表示。

字节与字符：

- ASCII 码中，一个英文字母（不分大小写）为一个字节，一个中文汉字为两个字节。
- UTF-8 编码中，一个英文字为一个字节，一个中文为三个字节。
- Unicode 编码中，一个英文为一个字节，一个中文为两个字节。
- 符号：英文标点为一个字节，中文标点为两个字节。例如：英文句号 . 占1个字节的大小，中文句号 。占2个字节的大小。
- UTF-16 编码中，一个英文字母字符或一个汉字字符存储都需要 2 个字节（Unicode 扩展区的一些汉字存储需要 4 个字节）。
- UTF-32 编码中，世界上任何字符的存储都需要 4 个字节。

