package cn.fancy.protobuf.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;

import cn.fancy.protobuf.PersonProbuf;
import cn.fancy.protobuf.PersonProbuf.Person;
import cn.fancy.protobuf.PersonProbuf.Person.PhoneNumber;
import cn.telling.utils.LogUtils;



/**   
* @Title: Snippet.java 
* @Package cn.fancy.protobuf.test 
* @Description: 
* 通过protobuf序列化后写入磁盘，以及从磁盘读取后反序列化的过程。
* protobuf把信息写入磁盘后，文件的大小为69字节，而用json表示大概有90多字节，用xml表示大概需150字节（在去掉xml换行及空格的情况下），
* 用此可见protobuf的威力了。当然，protobuf除了占用空间小外，还有速度快，使用简单等诸多优点。
* @author 操圣
* @date 2016-3-10 下午3:58:07 
* @version V1.0   
*/

public class TestPersonProbuf {

        /**
         * @param args
         * @throws Exception 
         */
        public static void main(String[] args) throws Exception {
            // 序列化过程
            // PersonProbuf是生成的数据访问类的名字，即proto文件中的java_outer_classname
            // Person是里面某个消息序列的名字，即proto文件中的message Person
            PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
            //设置消息序列中的字段值
            builder.setEmail("zhuxun777@gmail.com");
            builder.setId(320324);
            builder.setName("Jack Zhu");
            //phone字段在person.proto文件中被定义为repeated型，可以放多个值
            //（在本例中，phone字段的数据类型为消息类型PhoneNumber）
            builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("18762678793").setType(Person.PhoneType.HOME));
            builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("18651581021").setType(Person.PhoneType.HOME));
            
            Person person = builder.build();
            byte[] buf = person.toByteArray();
            
            //把序列化后的数据写入本地磁盘
            ByteArrayInputStream stream = new ByteArrayInputStream(buf);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("F:/protobuf.txt"));//设置输出路径
            BufferedInputStream bis = new BufferedInputStream(stream);
            int b = -1;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
            bis.close();
            bos.close();
            LogUtils.Log(TestPersonProbuf.class).debug("写入本地成功");
            
            
            //读取序列化后的数据
            try {
                Person person01 = PersonProbuf.Person.parseFrom(buf);
                List<PhoneNumber> phones = person01.getPhoneList();
                String strPhone = "";
                for(PhoneNumber phone : phones){
                    strPhone += phone.getNumber() + "   ";
                }
               String strResult = person01.getName() + "," + person01.getId() + "," + person01.getEmail() + "," + strPhone;
               System.out.println(strResult);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            
            //读取序列化后写入磁盘的数据
            try {
                BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream("F:/protobuf.txt"));
                byte b2 = -1;
                List<Byte> list = new LinkedList<Byte>();
                while ((b2 = (byte) bis2.read()) != -1) {
                    list.add(b2);
                }
                bis2.close();
                int length = list.size();
                byte[] byt = new byte[length];
                for(int i = 0; i < length; i++){
                    byt[i] = list.get(i);
                }
                Person person01 = PersonProbuf.Person.parseFrom(byt);
                List<PhoneNumber> phones = person01.getPhoneList();
                String strPhone = "";
                for(PhoneNumber phone : phones){
                    strPhone += phone.getNumber() + "   ";
                }
                String strResult = person01.getName() + "," + person01.getId() + "," + person01.getEmail() + "," + strPhone;
                System.out.println(strResult);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }

    }
