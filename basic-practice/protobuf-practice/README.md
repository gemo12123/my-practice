# protobuf-practice

### 1、 Protocal Buffers

​	 Protocal Buffers（简称protobuf）是谷歌的一项技术，用于**结构化的**数据序列化、反序列化。 

​	Protocal Buffers是一种语言无关、平台无关、可扩展的序列化结构数据的方法， 作为一种效率和兼容性都很优秀的**二进制数据传输格式**，可以用于诸如网络传输、配置文件、数据存储等诸多领域。

​	protobuf 有两个版本，分别是 proto2 和 proto3，一般主要使用proto3。

#### 特点

1、**语言无关、平台无关**。即 ProtoBuf 支持 Java、C++、Python 等多种语言，支持多个平台

2、**高效**。即比 XML 更小（3 ~ 10倍）、更快（20 ~ 100倍）、更为简单

3、**扩展性、兼容性好**。你可以更新数据结构，而不影响和破坏原有的旧程序

### 2、使用步骤

1. 下载编译器（配置Path环境变量）： https://github.com/protocolbuffers/protobuf/releases 

2. 编写`.proto`文件

3. 生成代码

   ```shell
   protoc --java_out=src/main/java src/main/resources/protobuf/Student.proto
   ```

   > `--java_out=src/main/java`指定了生成代码的路径

   > Idea中有相关插件可以更方便得生成代码

### 3、`.proto`文件解析

```protobuf
// 使用 proto3 语法 ,未指定则使用proto2
syntax = "proto3";

// 缺省时的包名，
// 如果下面没有设置java_package则会使用此设置，
// 如果设置了则不会使用。
package org.mytest.test.protobuf;

// 文件级别的选项，Protocol Buffer定义三种优化级别SPEED、CODE_SIZE、LITE_RUNTIME。
// SPEED: 默认级别。表示生成的代码运行效率高，但是由此生成的代码编译后会占用更多的空间。
option optimize_for =SPEED;
// 生成代码的包路径
option java_package = "org.mytest.test.protobuf";
// 生成Java类的名称，如果未指定该值，则会根据.proto文件的名称采用驼峰式的命名方式进行生成
option java_outer_classname = "StudentProtoBuf";

message Student {
  string name = 1;
  int32 age = 2;
  string address = 3;
}
```

> 注意`java_outer_classname`不能与`message`定义相同

* `syntax`：定义语法类型，未指定则默认使用proto2。必须是文件的第一个非空、非注释行。
* `import` ：导入
* `package`：逻辑包，应用于protobuf对于文件的管理；也是缺省时的包名，如果下面没有设置`java_package` 则会使用此设置，如果设置了则不会使用。
* `option`
  * `optimize_for`：文件级别的选项，Protocol Buffer定义三种优化级别SPEED、CODE_SIZE、LITE_RUNTIME。
    * SPEED: 默认级别。表示生成的代码运行效率高，但是由此生成的代码编译后会占用更多的空间。
  * `java_package`：生成代码的包路径
  * `java_multiple_files`：生产的Java类是一个源文件还是多个源文件。
  * `java_outer_classname`：生成Java外层类的名称，如果未指定该值，则会根据`.proto`文件的名称采用驼峰式的命名方式进行生成。==不能与`message`同名。==
* `message`
  * 字段标签：`singular` 、`optional`、`repeated`、`map`
  * 字段定义： 每个字段都有一个**唯一的编号（字段编码）**，用于在消息二进制格式中标识该字段。
    * 标量类型
    *  复合类型
      * enum枚举
      *  其他消息类型

#### 1.字段编码

​	字段编码1-15需要一个字节进行编码，16-2047需要两个字节进行编码，建议频繁出现的元素字段编码为1-15。 字段编码最小值 1，最大字段编号为 ((2^29) - 1)，即 536,870,911。

**限制：**

*  给定的数字在该消息的所有字段中必须是**唯一的**。 
* 数字 19000 到 19999 是为 Protocol Buffers 保留值，不能使用。
*  不能使用任何以前保留的字段编号或已分配给扩展的任何字段编号。 

>  使用消息类型后，无法更改此数字，因为它标识消息线格式的字段。 

>  重用字段编号会使解码有线格式消息变得不明确。 

#### 2.字段标签

消息字段可以是以下标签之一：

* `singular`： proto3的默认规则， 可以有0或1个该字段。 
* `optional`:   与`singular`相同，只是==可以检查该值是否已明确设置==。`optional`字段处于两种状态间： 
  *  该字段已设置，并包含一个明确设置或从序列化信息中解析的值。它将被序列化。
  *  该字段未设置，并将返回默认值。 不会被序列化。
* `repeated`：此字段类型可以在格式正确的消息中重复零次或多次。重复值的顺序将被保留。 
* `map`：成对的键/值字段类型

#### 3.字段定义

##### 标量值类型

| .proto Type | Notes                                                        | Java/Kotlin Type | C++ Type | Python Type                     | Go Type | Ruby Type                      | C# Type    | PHP Type          | Dart Type |
| ----------- | ------------------------------------------------------------ | ---------------- | -------- | ------------------------------- | ------- | ------------------------------ | ---------- | ----------------- | --------- |
| double      |                                                              | double           | double   | float                           | float64 | Float                          | double     | float             | double    |
| float       |                                                              | float            | float    | float                           | float32 | Float                          | float      | float             | double    |
| int32       | 可变长度编码。编码负数效率低下——如果您的字段可能有负值，请改用 sint32。 | int              | int32    | int                             | int32   | Fixnum or Bignum (as required) | int        | integer           | int       |
| int64       | 使用可变长度编码。编码负数效率低下——如果您的字段可能有负值，请改用 sint64。 | long             | int64    | int/long[4]                     | int64   | Bignum                         | long       | integer/string[6] | Int64     |
| uint32      | 使用可变长度编码。                                           | int[2]           | uint32   | int/long[4]                     | uint32  | Fixnum or Bignum (as required) | uint       | integer           | int       |
| uint64      | 使用可变长度编码。                                           | long[2]          | uint64   | int/long[4]                     | uint64  | Bignum                         | ulong      | integer/string[6] | Int64     |
| sint32      | 使用可变长度编码。有符号的 int 值。这些比常规的 int32 更有效地编码负数。 | int              | int32    | int                             | int32   | Fixnum or Bignum (as required) | int        | integer           | int       |
| sint64      | 使用可变长度编码。有符号的 int 值。这些比常规的 int64 更有效地编码负数。 | long             | int64    | int/long[4]                     | int64   | Bignum                         | long       | integer/string[6] | Int64     |
| fixed32     | 总是四个字节。如果值通常大于 2^28 ，则比 uint32 更有效。     | int[2]           | uint32   | int/long[4]                     | uint32  | Fixnum or Bignum (as required) | uint       | integer           | int       |
| fixed64     | 总是八个字节。如果值通常大于 2^56 ，则比 uint64 更有效。     | long[2]          | uint64   | int/long[4]                     | uint64  | Bignum                         | ulong      | integer/string[6] | Int64     |
| sfixed32    | 总是四个字节。                                               | int              | int32    | int                             | int32   | Fixnum or Bignum (as required) | int        | integer           | int       |
| sfixed64    | 总是八个字节。                                               | long             | int64    | int/long[4]                     | int64   | Bignum                         | long       | integer/string[6] | Int64     |
| bool        |                                                              | boolean          | bool     | bool                            | bool    | TrueClass/FalseClass           | bool       | boolean           | bool      |
| string      | 字符串必须始终包含 UTF-8 编码或 7 位 ASCII 文本，并且不能长于 2^32。 | String           | string   | str/unicode[5]                  | string  | String (UTF-8)                 | string     | string            | String    |
| bytes       | 可能包含不超过 2^32的任意字节序列。                          | ByteString       | string   | str (Python 2) bytes (Python 3) | []byte  | String (ASCII-8BIT)            | ByteString | string            | List      |

> - 对于字符串，默认值为空字符串。
> - 对于字节，默认值为空字节。
> - 对于布尔值，默认值为 false。
> - 对于数字类型，默认值为零。
> - 对于枚举，默认值是**第一个定义的枚举值**，它必须是 0。
> - 对于消息字段，该字段未设置。它的确切值取决于语言。

##### 枚举类型

```protobuf
enum EnumAllowingAlias {
  option allow_alias = true;
  EAA_UNSPECIFIED = 0;
  EAA_STARTED = 1;
  EAA_RUNNING = 1;
  EAA_FINISHED = 2;
}

enum EnumNotAllowingAlias {
  ENAA_UNSPECIFIED = 0;
  ENAA_STARTED = 1;
  // ENAA_RUNNING = 1;  // Uncommenting this line will cause a warning message.
  ENAA_FINISHED = 2;
}
```

​	每个枚举定义都==**必须**包含一个映射到0的常量作为其第一个元素==（必须有一个零值是为了将0作为默认值，作为其第一个元素是为了兼容proto2）。 

> ​	 可以通过将相同的值分配给不同的枚举常量来定义别名。为此，需要将`allow_alias`选项设置为`true` ，否则协议编译器会在发现别名时生成一条警告消息（尽管在反序列化期间所有别名值都有效，但在序列化时始终使用第一个值）。
>

​	枚举数常量必须在 32 位整数范围内。由于值在线路上`enum` 使用 varint 编码，因此负值效率低下，不推荐使用。

>  生成的代码可能会受到特定于语言的枚举器数量限制（一种语言的数量很少）。 

> 枚举中通过 `reserved`  指定已删除条目的数值。 如果任何未来的用户试图使用这些标识符，protocol buffer 编译器将会报错。
>
> ```protobuf
> enum Foo {
>   reserved 2, 15, 9 to 11, 40 to max;
>   reserved "FOO", "BAR";
> }
> ```
>
>  不能在同一语句中混合使用字段名称和数值`reserved`  

##### 嵌套类型

 可以在其他消息类型中定义和使用消息类型，如以下示例所示：

```protobuf
message SearchResponse {
  message Result {
    string url = 1;
    string title = 2;
    repeated string snippets = 3;
  }
  repeated Result results = 1;
}
```

 如果要在其父消息类型之外重用此消息类型，请将其称为`_Parent_._Type_`： 

```protobuf
message SomeOtherMessage {
  SearchResponse.Result result = 1;
}
```

##### 未知字段

​	消息类型允许您将 `Any` 消息用作嵌入类型，而无需定义其 .proto。`Any` 包含任意序列化消息 ，以及一个 URL `bytes` ，该 URL 充当该消息的全局唯一标识符并解析为该消息的类型。要使用该 `Any` 类型，您需要导入 `google/protobuf/any.proto` 。

```protobuf
import "google/protobuf/any.proto";

message ErrorStatus {
  string message = 1;
  repeated google.protobuf.Any details = 2;
}
```

##### Map

​	 创建关联映射作为数据定义的一部分，protobuf 提供了一个方便的快捷语法： 

```protobuf
map<key_type, value_type> map_field = N;
```

*  其中 `key_type` 可以是任何整数或字符串类型（即除浮点类型和`bytes`之外的任何标量类型  ）
*  枚举不是有效的 `key_type` 
*  `value_type` 可以是除map之外的任何类型。
*  映射字段不能为 `repeated` 。
* 

##### Oneof

 	包含许多字段的消息，并且最多同时设置一个字段，则可以强制使用此行为并使用 `oneof` 功能节省内存。 **如果设置了多个值，则由原型中的顺序确定的最后一个设置值将覆盖所有先前的值。** 

```protobuf
message SimpleMessage {
	oneof test_oneof {
		string name = 1;
		int32 age = 2;
	}
	test_oneof 
}
```

#### 4.公共导入

> 可以使用其他消息类型作为字段类型，如果消息类型在同一个文件中则无需导入，否则需要通过 `import`关键字进行导入，如 `import "myproject/other_protos.proto";` 。IDEA 中如果无法导入，可能需要修改项目配置的源根目录作为 protobuf 导入路径： `Settings > Languages & Frameworks > Protocol Buffers`  
>

> 可以导入 proto2 消息类型并在 proto3 消息中使用它们，反之亦然。但是，不能在 proto3 语法中直接使用 proto2 枚举（如果导入的 proto2 消息使用它们也没关系）。 

​	 默认情况下，只能使用直接导入`.proto`文件中的定义。但是，有时可能需要将`.proto`文件移动到新位置。 可以将占位符文件放在旧位置，以使用 `import public` 将所有导入转发到新位置，而不是`.proto`直接移动文件并在一次更改中更新所有调用站点。 

> **请注意，公共导入功能在 Java 中不可用。** 

```
// new.proto
// All definitions are moved here
```

```
// old.proto
// This is the proto that all clients are importing.
import public "new.proto";
import "other.proto";
```

```
// client.proto
import "old.proto";
// You use definitions from old.proto and new.proto, but not other.proto
```

>  `--proto_pat/ -I` 限定编译器指定搜索导入的目录

#### 5.删除和保留字段

##### 删除字段

​	如果不再需要非必填字段，并且已从客户端代码中删除所有引用，则可以从消息中删除字段定义。但是，您必须保留已删除的字段编号。如果不保留字段编号，则开发人员将来可能会重复使用该编号。您还应该保留字段名称，以允许消息的 JSON 和文本格式编码继续解析。 

##### 保留字段

​	可以将已删除的字段编号添加到 `reserved` 列表中。若要确保仍然可以解析消息的 JSON 和 TextFormat 实例，还要将已删除的字段名称添加到 `reserved` 列表中。 

> 不能在同一 `reserved` 语句中混合使用字段名称和字段编号 

#### 6.message

```protobuf
message SearchRequest {
  string query = 1;
  int32 page_number = 2;
  int32 results_per_page = 3;
}

message SearchResponse {
 ...
}
```

#### 7.service

```protobuf
service SearchService {
  rpc search(SearchRequest) returns (SearchResponse);
}
```

> 使用 gRPC 时，可以通过给请求/返回值前面加一个`stream`关键字，声明该方法是客户端/服务端流式RPC

### 4.Maven插件

```xml
<plugin>
    <groupId>org.xolstice.maven.plugins</groupId>
    <artifactId>protobuf-maven-plugin</artifactId>
    <version>0.6.1</version>

    <configuration>
        <!-- proto文件目录 -->
        <protoSourceRoot>${basedir}/src/main/proto</protoSourceRoot>
        <!-- 生成的Java文件目录，默认是${project.build.directory}/generated-sources/protobuf -->
        <outputDirectory>${basedir}/src/main/java/</outputDirectory>
        <!-- 是否清空输出文件，默认true（false即为追加式生成） -->
        <clearOutputDirectory>false</clearOutputDirectory>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>test-compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

> 当使用gRPC时，通过在configuration中配置pluginId，可以直接输出service

### 5.注意事项

* 序列化

  1. 引入依赖

     ```xml
     <dependency>
         <groupId>com.google.protobuf</groupId>
         <artifactId>protobuf-java-util</artifactId>
         <!--<scope>runtime</scope>-->
     </dependency>
     ```

  2. 执行序列化

     ```java
     JsonFormat.printer().includingDefaultValueFields().print(response); //建议加上includingDefaultValueFields()配置项，否则枚举字段可能无法序列化
     ```

     