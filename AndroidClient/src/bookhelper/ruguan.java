package com.stdu.edu.italk.bookhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stdu.edu.italk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ruguan extends Activity {
	  private ListView listview;
      private ArrayAdapter<String> adapter;
	  public TextView ProblemName;
	  String unm = " ";
	  public TextView Problemhuizong;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ruguan);
	        Intent intent = getIntent();
	        unm = intent.getStringExtra("USERNAME");
	        ProblemName = (TextView)findViewById(R.id.problemname);
	        Problemhuizong = (TextView)findViewById(R.id.problemhuizong);
	        ProblemName.setText(unm+"同学您好！，下面是常见问题汇总：");
	        Problemhuizong.setText("1、学生、教师分别可以借几本书？借期是多长？ \n2、如何借阅馆藏图书和期刊？ \n3、馆藏书目信息显示的“书刊状态”如何理解？ \n4、在办理图书外借手续时需注意的事项？\n5、校园卡在借阅区（室）出现问题时该怎么办？ \n6、校园卡遗失了如何办理挂失？ \n7、如何办理校园卡清退手续？\n8、如何借阅随书（刊）光盘？ \n9、所借书刊遗失如何赔偿？\n ");
	        listview = (ListView)findViewById(R.id.listproblem);
	        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData());
	        listview.setAdapter(adapter);
}
	 private List<String> getData(){
	    	List<String> data = new ArrayList<String>();
	    	data.add("1、学生、教师分别可以借几本书？借期是多长？\n 教师、研究生每证限借15册（高级职称者限借20册）；学生每证限借7册（因材施教班学生12册）。 教职工、研究生读者借期150天（到期后可续借30天）；学生读者借期30天（可续借30天）。 ");
	    	data.add("2、如何借阅馆藏图书和期刊？\n 读者可先在图书馆大厅所设置的检索台（或登录图书馆网页在“联机目录”栏目下点击“书目查询”）通过图书馆书目检索系统检索所需书（刊），了解所需书（刊）的馆藏状态，并记录书（刊）的索书号和馆藏地，然后进入相应的借阅区（室），按索书号排架次序从书架上选取自已所需要的书（刊），可在阅览区阅览，如果外借，需凭本人校园卡在出纳台办理外借手续。（注：馆藏期刊的检索方法与图书基本相同，排架规则为现按年代，再按分类号，再按刊名字顺。）");
	    	data.add("3、馆藏书目信息显示的“书刊状态”如何理解？\n 读者使用“图书馆书目检索系统”检索到某本书的书目信息时，页面下方表格最右侧一栏显示某本书的“书刊状态”信息，其中“订购”是指该书只是已经订购，在出版中或者已经出版，尚未进馆，索书号栏为空，无法预计与读者见面的时间；“待编”是指图书已经到馆验收，未曾分类编目，索书号栏为空，一般而言，很快就能与读者见面；“在编”是指图书已经到馆验收，正在分类编目，索书号栏为空，即将与读者见面；“可借”是指该书已经完成分类、编目，有完整的索书号，已经进入书库流通。“非可借”是指该书属于馆存本，只供室内阅览，不提供外借服务。");
	    	data.add("4、在办理图书外借手续时需注意的事项？ \n（1）须凭本人校园卡办理，禁止使用他人校园卡借书。\n（2）读者须自行检查所借图书是否完好，如发现勾划、破损等现象，须向工作人员索要并加盖相应勾画破损印章。否则，还书时出现勾划、破损等未加盖破损印章现象，工作人员将依照“读者借阅书刊污损、遗失及窃书处理规定”处理。");
	    	data.add("5、校园卡在借阅区（室）出现问题时该怎么办？\n 读者在办理借书手续时，如果微机显示“无此读者”或读不出信息时，请到还书处帮助查找原因。");
	    	data.add("6、校园卡遗失了如何办理挂失？\n 可到图书馆还书处办理挂失手续。对于校园卡遗失挂失前出现的冒借现象，所造成的经济损失及责任由遗失校园卡者本人承担。如在已挂失期间找回校园卡，应到还书处办理解挂手续，取消挂失后，方可继续使用。");
	    	data.add("7、如何办理校园卡清退手续？\n读者调离、退学、毕业离校时，须在离校前10天将所借图书归还，如有图书遗失现象，须办理赔偿手续后，方可凭离校单在还书处或流通阅览部办公室办理清退手续。");
	    	data.add("8、如何借阅随书（刊）光盘？ \n（1）外借随书光盘：在已知随书光盘索书号时，可根据索书号到存放相应图书的借阅区办理外接手续。外借随刊光盘：在中文过刊阅览室（东区三层）办理借阅手续。 \n（2）网上下载阅览随书光盘：登录图书馆网页，进入“联机目录”下的“非书资料（随书光盘）查询”页面，点击“光盘资源”，可按分类浏览或高级检索查找所需光盘，并按说明下载客户端或虚拟光驱软件后，方可浏览光盘内容。");
	    	data.add("9、所借书刊遗失如何赔偿？\n 读者遗失所借书、刊，依照“读者借阅书刊污损、遗失及窃书处理规定”处理。");
	    	return data;
	    }
}
